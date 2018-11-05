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
        Settings emissionfactorSettings = new Settings("emissionfactor", 50);
        Settings speedfactorSettings = new Settings("speedfactor", 1);
        Settings emissionTimeframeSettings = new Settings("emissionTimeframe", 24);
        Settings speedTimeframeSettings = new Settings("speedTimeframe", 30);
        settingsService.save(emissionfactorSettings);
        settingsService.save(speedfactorSettings);
        settingsService.save(emissionTimeframeSettings);
        settingsService.save(speedTimeframeSettings);
    }

    @PostConstruct
    private void postAdmin() {
        User user = new User("username", "password");
        userService.save(user);
    }
}