package itsm.springGreetingsServer.logic.requestProcessors;

import itsm.springGreetingsServer.messages.SimpleGreetingsRequest;
import org.springframework.stereotype.Service;

@Service
public class SpecifiedGreetingsRequestProcessorServiceImpl implements GreetingsRequestProcessorService {

    @Override
    public String processRequest(SimpleGreetingsRequest request) {
        String message = request.getMessage();
        String name = request.getName();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("message from: %s, content: %s", name, message));
        return ("Hello, " + name);
    }

    @Override
    public boolean acceptRequest(SimpleGreetingsRequest simpleGreetingsRequest) {
        return true;
    }
}
