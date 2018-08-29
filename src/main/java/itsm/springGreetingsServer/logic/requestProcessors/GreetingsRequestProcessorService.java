package itsm.springGreetingsServer.logic.requestProcessors;

import itsm.springGreetingsServer.messages.SimpleGreetingsRequest;

public interface GreetingsRequestProcessorService {

    String processRequest(SimpleGreetingsRequest request);

    boolean acceptRequest(SimpleGreetingsRequest simpleGreetingsRequest);
}
