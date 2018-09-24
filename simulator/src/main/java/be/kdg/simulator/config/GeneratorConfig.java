package be.kdg.simulator.config;

import be.kdg.simulator.generators.FileGenerator;
import be.kdg.simulator.generators.MessageGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratorConfig {

    @Bean
    @ConditionalOnProperty(name = "generator.type", havingValue = "file")
    //De naam van deze bean is default de naam van deze methode
    public MessageGenerator fileGenerator() {
        return new FileGenerator();
    }
}
