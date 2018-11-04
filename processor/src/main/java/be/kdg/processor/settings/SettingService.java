package be.kdg.processor.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SettingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettingService.class);

    private final SettingRepository settingRepository;

    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    public Setting save(Setting setting) {
        return settingRepository.save(setting);
    }

    public Setting load(String property) throws SettingException {
        Optional<Setting> optionalSetting = Optional.ofNullable(settingRepository.findByProperty(property));
        if (optionalSetting.isPresent()) {
            return optionalSetting.get();
        }
        throw new SettingException("Setting not found.");
    }

    public List<Setting> getSettings() {
        return settingRepository.findAll();
    }

    public int getEmissionFactor() {
        try {
            Setting setting = load("emissionfactor");
            return setting.getValue();
        } catch (SettingException e) {
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    public int getSpeedFactor() {
        try {
            Setting setting = load("speedfactor");
            return setting.getValue();
        } catch (SettingException e) {
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    public int getEmissionTimeframe() {
        try {
            Setting setting = load("emissionTimeframe");
            return setting.getValue();
        } catch (SettingException e) {
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    public int getSpeedTimeframe() {
        try {
            Setting setting = load("speedTimeframe");
            return setting.getValue();
        } catch (SettingException e) {
            LOGGER.error(e.getMessage());
        }
        return 0;
    }
}
