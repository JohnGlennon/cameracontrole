package be.kdg.processor.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SettingsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsService.class);

    private final SettingsRepository settingsRepository;

    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public Settings save(Settings settings) {
        return settingsRepository.save(settings);
    }

    public Settings load(String property) throws SettingsException {
        Optional<Settings> optionalSetting = Optional.ofNullable(settingsRepository.findByProperty(property));
        if (optionalSetting.isPresent()) {
            return optionalSetting.get();
        }
        throw new SettingsException("Settings not found.");
    }

    public List<Settings> getSettings() {
        return settingsRepository.findAll();
    }

    public int getEmissionFactor() {
        try {
            Settings settings = load("emissionfactor");
            return settings.getValue();
        } catch (SettingsException e) {
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    public int getSpeedFactor() {
        try {
            Settings settings = load("speedfactor");
            return settings.getValue();
        } catch (SettingsException e) {
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    public int getEmissionTimeframe() {
        try {
            Settings settings = load("emissionTimeframe");
            return settings.getValue();
        } catch (SettingsException e) {
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    public int getSpeedTimeframe() {
        try {
            Settings settings = load("speedTimeframe");
            return settings.getValue();
        } catch (SettingsException e) {
            LOGGER.error(e.getMessage());
        }
        return 0;
    }
}
