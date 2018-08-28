package itsm.springGreetingsServer.logic.requestProcessors;

import itsm.springGreetingsServer.messages.SimpleGreetingsRequest;
import itsm.springGreetingsServer.messages.SimpleGreetingsResponse;

public interface GreetingsRequestProcessorService {

    SimpleGreetingsResponse processRequest(SimpleGreetingsRequest request);

    boolean acceptRequest(SimpleGreetingsRequest simpleGreetingsRequest);
}
