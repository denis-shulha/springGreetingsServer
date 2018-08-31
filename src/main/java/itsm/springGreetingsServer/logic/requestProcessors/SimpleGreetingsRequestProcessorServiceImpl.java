package itsm.springGreetingsServer.logic.requestProcessors;

import itsm.springGreetingsServer.logic.Sleeper.ServerResponseSleeper;
import itsm.springGreetingsServer.messages.SimpleGreetingsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleGreetingsRequestProcessorServiceImpl implements GreetingsRequestProcessorService {

    private ServerResponseSleeper sleeper;

    @Autowired
    public SimpleGreetingsRequestProcessorServiceImpl(ServerResponseSleeper sleeper) {
        this.sleeper = sleeper;
    }

    @Override
    public String processRequest(SimpleGreetingsRequest request) {
        String message = request.getMessage();
        String name = request.getName();

        try {
            sleeper.sleep();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("message from: %s, content: %s", name, message));
        return ("Hello, " + name);
    }

    @Override
    public boolean acceptRequest(SimpleGreetingsRequest simpleGreetingsRequest) {

        return !simpleGreetingsRequest.getMessage().contains("specified");
    }
}
