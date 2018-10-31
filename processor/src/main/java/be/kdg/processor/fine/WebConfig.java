package be.kdg.processor.fine;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/summary").setViewName("summary");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/factors").setViewName("factors");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
