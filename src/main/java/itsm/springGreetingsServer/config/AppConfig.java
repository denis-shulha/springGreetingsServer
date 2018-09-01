package itsm.springGreetingsServer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import itsm.springGreetingsServer.logic.sleeper.ServerResponseSleeper;
import itsm.springGreetingsServer.logic.requestProcessors.GreetingsRequestProcessorService;
import itsm.springGreetingsServer.logic.server.SpringGreetingsServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.inject.Provider;
import java.util.List;

@Configuration
@ComponentScan("itsm.springGreetingsServer")
@PropertySource("classpath:server.properties")
public class AppConfig {

    @Value("${server.port}")
    private Integer port;

    @Value("${server.thread.limit}")
    private Integer threadLimit;

    @Value("${spring.activeProfiles}")
    private String activeProfiles;

    @Value("${server.response.delay}")
    private Integer delay;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ServerResponseSleeper sleeper() {
        return new ServerResponseSleeper(delay);
    }

    @Bean
    public SpringGreetingsServer springGreetingsServer(
            ObjectMapper mapper,
            ServerResponseSleeper sleeper,
            Provider<List<GreetingsRequestProcessorService>> provider) {
        return new SpringGreetingsServer(
                port,
                threadLimit,
                mapper,
                provider);
    }
}
