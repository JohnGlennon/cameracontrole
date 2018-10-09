package be.kdg.processor.config;

import be.kdg.sa.services.CameraServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CameraServiceConfig {
    @Bean
    public CameraServiceProxy getCameraServiceProxy() {
        return new CameraServiceProxy();
    }
}
