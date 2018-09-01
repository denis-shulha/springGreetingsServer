package itsm.springGreetingsServer.logic.requestProcessors;

import itsm.springGreetingsServer.logic.sleeper.ServerResponseSleeper;
import itsm.springGreetingsServer.messages.SimpleGreetingsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecifiedGreetingsRequestProcessorServiceImpl implements GreetingsRequestProcessorService {

    private ServerResponseSleeper sleeper;

    @Autowired
    public SpecifiedGreetingsRequestProcessorServiceImpl(ServerResponseSleeper sleeper) {
        this.sleeper = sleeper;
    }

    @Override
    public String processRequest(SimpleGreetingsRequest request) {
        String message = request.getMessage();
        String name = request.getName();
        sleeper.sleep();

        System.out.println(String.format("message from: %s, content: %s", name, message));
        return ("Greetings, " + name);
    }

    @Override
    public boolean acceptRequest(SimpleGreetingsRequest simpleGreetingsRequest) {
        return simpleGreetingsRequest.getMessage().contains("specified");
    }
}
