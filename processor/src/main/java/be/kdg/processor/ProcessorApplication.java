package be.kdg.processor;

import be.kdg.processor.security.User;
import be.kdg.processor.security.UserService;
import be.kdg.processor.settings.Settings;
import be.kdg.processor.settings.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

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
        settings.addSetting("retryCount", 5);
        settingsService.save(settings);
    }

    @PostConstruct
    private void postAdmin() {
        User user = new User("username", "password");
        userService.save(user);
    }
}