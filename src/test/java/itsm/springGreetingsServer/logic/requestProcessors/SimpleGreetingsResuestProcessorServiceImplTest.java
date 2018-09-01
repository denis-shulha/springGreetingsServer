package itsm.springGreetingsServer.logic.requestProcessors;

import itsm.springGreetingsServer.logic.sleeper.ServerResponseSleeper;
import itsm.springGreetingsServer.messages.SimpleGreetingsRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.Assert;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SimpleGreetingsResuestProcessorServiceImplTest {

    @Mock
    private ServerResponseSleeper sleeper;

    @Before
    public void init() {
        when(sleeper.sleep()).thenReturn(1);
    }

    @InjectMocks
    private SimpleGreetingsRequestProcessorServiceImpl simpleGreetingsRequestProcessorService;

    @Test
    public void acceptRequestTest() {
        SimpleGreetingsRequest rightRequest = new SimpleGreetingsRequest("Tim", "Hello, Server!");
        SimpleGreetingsRequest wrongRequest = new SimpleGreetingsRequest("Tim", "Hello specified, Server!");

        Assert.isTrue(simpleGreetingsRequestProcessorService
                .acceptRequest(rightRequest),"Wrong request");
        Assert.isTrue(!simpleGreetingsRequestProcessorService
                .acceptRequest(wrongRequest),"Wrong request");

        String response = simpleGreetingsRequestProcessorService.processRequest(rightRequest);

        Assert.notNull(response,"Can't process request");

        verify(sleeper, times(1)).sleep();
    }
}
