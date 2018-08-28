package itsm.springGreetingsServer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import itsm.springGreetingsServer.logic.requestProcessors.GreetingsRequestProcessorService;
import itsm.springGreetingsServer.logic.server.SpringGreetingsServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.inject.Provider;
import java.util.List;

@Configuration
@ComponentScan("itsm.springGreetingsServer")
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public SpringGreetingsServer springGreetingsServer(
            ObjectMapper mapper,
            Provider<List<GreetingsRequestProcessorService>> provider) {
        return new SpringGreetingsServer(
                5678,
                10,
                mapper,
                provider);
    }
}
