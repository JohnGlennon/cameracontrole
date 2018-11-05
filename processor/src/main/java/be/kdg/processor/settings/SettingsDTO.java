package be.kdg.processor.settings;

import java.util.HashMap;
import java.util.Map;

public class SettingsDTO {

    private Map<String, Integer> settings;

    public SettingsDTO() {
        settings = new HashMap<>();
    }

    public SettingsDTO(Map<String, Integer> settings) {
        this.settings = settings;
    }

    public void addSetting(String property, int value) {
        settings.put(property, value);
    }

    public Map<String, Integer> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, Integer> settings) {
        this.settings = settings;
    }
}
