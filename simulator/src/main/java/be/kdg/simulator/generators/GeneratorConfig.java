package be.kdg.simulator.generators;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratorConfig {

    @Bean
    //De naam van deze bean is default de naam van deze methode
    public MessageGenerator fileGenerator() {
        return new FileGenerator();
    }
}
