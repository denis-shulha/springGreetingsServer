package itsm.springGreetingsServer.logic.Sleeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class ServerResponseSleeper {

    private Integer delay;

    @Autowired
    public ServerResponseSleeper(Integer delay) {
        this.delay = delay;
    }

    public void sleep() throws InterruptedException {
        Thread.sleep(delay);
    }
}
