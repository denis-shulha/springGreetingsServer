package itsm.springGreetingsServer.logic.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import itsm.springGreetingsServer.logic.requestProcessors.GreetingsRequestProcessorService;
import itsm.springGreetingsServer.messages.SimpleGreetingsRequest;
import itsm.springGreetingsServer.messages.SimpleGreetingsResponse;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpringGreetingsServer  implements Runnable{

    private int port;
    private ExecutorService executorService;
    private final int threadCount;
    private ServerSocket serverSocket;
    private final ObjectMapper objectMapper;
    private final Provider<List<GreetingsRequestProcessorService>> provider;

    public SpringGreetingsServer(int port,
                                 int threadCount,
                                 ObjectMapper objectMapper,
                                 Provider<List<GreetingsRequestProcessorService>> provider) {
        this.port = port;
        this.threadCount = threadCount;
        this.objectMapper = objectMapper;
        this.provider = provider;
    }

    @PostConstruct
    public void init() throws IOException {
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(threadCount);
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdownNow();
    }

    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            accept(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void accept(Socket socket) {
        executorService.submit(() -> {
            try {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                InputStreamReader reader = new InputStreamReader(is);
                OutputStreamWriter writer = new OutputStreamWriter(os);

                BufferedReader br = new BufferedReader(reader);
                SimpleGreetingsRequest request = objectMapper.readValue(br.readLine(), SimpleGreetingsRequest.class);

                List<GreetingsRequestProcessorService> processors = provider.get();

                SimpleGreetingsResponse response = null;

                for (GreetingsRequestProcessorService processor : processors) {
                    if (processor.acceptRequest(request)) {
                        response = processor.processRequest(request);
                        break;
                    }
                }
                String responseString = objectMapper.writeValueAsString(response);

                writer.write(responseString);
                writer.flush();

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}