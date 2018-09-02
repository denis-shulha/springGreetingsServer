package itsm.serverMain;

import itsm.springGreetingsServer.config.AppConfig;
import itsm.springGreetingsServer.logic.server.SpringGreetingsServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(SpringGreetingsServer.class).run();
        context.registerShutdownHook();

        SpringGreetingsServer server = context.getBean(SpringGreetingsServer.class);
        while (true) {
            server.run();
        }
    }
}
