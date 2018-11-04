package be.kdg.processor;

import be.kdg.processor.security.User;
import be.kdg.processor.security.UserService;
import be.kdg.processor.settings.Setting;
import be.kdg.processor.settings.SettingService;
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
    private SettingService settingService;

    @Autowired
    private UserService userService;

    @PostConstruct
    private void postFineFactors() {
        Setting emissionfactorSetting = new Setting("emissionfactor", "50");
        Setting speedfactorSetting = new Setting("speedfactor", "1");
        settingService.save(emissionfactorSetting);
        settingService.save(speedfactorSetting);
    }

    @PostConstruct
    private void postAdmin() {
        User user = new User("username", "password");
        userService.save(user);
    }
}