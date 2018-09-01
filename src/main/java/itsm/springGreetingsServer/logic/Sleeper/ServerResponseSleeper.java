package itsm.springGreetingsServer.logic.sleeper;

import org.springframework.beans.factory.annotation.Autowired;

public class ServerResponseSleeper {

    private Integer delay;

    @Autowired
    public ServerResponseSleeper(Integer delay) {
        this.delay = delay;
    }

    public Integer sleep() {
        try {
            Thread.sleep(delay);
            return 1;
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            return 0;
        }

    }
}
