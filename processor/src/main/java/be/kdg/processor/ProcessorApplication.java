package be.kdg.processor;

import be.kdg.processor.security.User;
import be.kdg.processor.security.UserService;
import be.kdg.processor.settings.Settings;
import be.kdg.processor.settings.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import javax.annotation.PostConstruct;

@EnableRetry
@SpringBootApplication
public class ProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProcessorApplication.class, args);
    }

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private UserService userService;

    @PostConstruct
    private void postSettings() {
        Settings settings = new Settings();
        settings.addSetting("emissionFactor", 50);
        settings.addSetting("speedFactor", 1);
        settings.addSetting("emissionTimeframe", 24);
        settings.addSetting("speedTimeframe", 30);
        settings.addSetting("retryCount", 3);
        settings.addSetting("retryDelay", 500);
        settingsService.save(settings);
    }

    @PostConstruct
    private void postAdmin() {
        User user = new User("username", "password");
        User testuser = new User("testuser", "testpwd");
        userService.save(user);
        userService.save(testuser);
    }

    @Bean
    public RetryTemplate retryTemplate() {
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(settingsService.getRetryCount());

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(settingsService.getRetryDelay());

        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(retryPolicy);
        template.setBackOffPolicy(backOffPolicy);

        return template;
    }
}