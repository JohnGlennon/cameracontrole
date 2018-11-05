package be.kdg.processor.settings;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SettingsService {

    private final SettingsRepository settingsRepository;

    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public void save(Settings settings) {
        settingsRepository.save(settings);
    }

    public Settings getSettings() {
        return settingsRepository.findAll().get(0);
    }

    public int getEmissionFactor() {
        Settings settings = getSettings();
        return settings.getEmissionFactor();
    }

    public int getSpeedFactor() {
        Settings settings = getSettings();
        return settings.getSpeedFactor();
    }

    public int getEmissionTimeframe() {
        Settings settings = getSettings();
        return settings.getEmissionTimeframe();
    }

    public int getSpeedTimeframe() {
        Settings settings = getSettings();
        return settings.getSpeedTimeframe();
    }

    public void updateSettings(Settings newSettings) {
        Settings oldSettings = getSettings();
        oldSettings.setSettings(newSettings.getSettings());
        save(oldSettings);
    }
}
