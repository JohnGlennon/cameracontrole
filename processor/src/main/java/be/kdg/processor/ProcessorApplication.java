package be.kdg.processor;

import be.kdg.processor.fine.Fine;
import be.kdg.processor.fine.FineService;
import be.kdg.processor.offense.Offense;
import be.kdg.processor.offense.OffenseType;
import be.kdg.processor.security.User;
import be.kdg.processor.security.UserService;
import be.kdg.processor.settings.Settings;
import be.kdg.processor.settings.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@SpringBootApplication
public class ProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProcessorApplication.class, args);
    }

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private UserService userService;

    @Autowired
    private FineService fineService;

    @PostConstruct
    private void postSettings() {
        Settings settings = new Settings();
        settings.addSetting("emissionFactor", 50);
        settings.addSetting("speedFactor", 1);
        settings.addSetting("emissionTimeframe", 24);
        settings.addSetting("speedTimeframe", 30);
        settings.addSetting("retryCount", 3);
        settingsService.save(settings);
    }

    @PostConstruct
    private void postAdmin() {
        User user = new User("username", "password");
        User testuser = new User("testuser", "testpwd");
        userService.save(user);
        userService.save(testuser);
    }

    @PostConstruct
    private void postFine() {
        Fine fine = new Fine(10L, new Offense("1-ABC-123", LocalDateTime.now(), OffenseType.EMISSION), 50);
        fineService.save(fine);
    }
}