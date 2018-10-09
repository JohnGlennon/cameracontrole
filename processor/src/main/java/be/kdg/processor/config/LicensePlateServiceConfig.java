package be.kdg.processor.config;

import be.kdg.sa.services.LicensePlateServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LicensePlateServiceConfig {
    @Bean
    public LicensePlateServiceProxy getLicensePlateServiceProxy() {
        return new LicensePlateServiceProxy();
    }
}
