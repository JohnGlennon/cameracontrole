package be.kdg.simulator.config;

import be.kdg.simulator.generators.FileGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Bean
    @ConditionalOnProperty(name = "generator.type", havingValue = "file")
    public FileGenerator fileGenerator() {
        return new FileGenerator();
    }
}
