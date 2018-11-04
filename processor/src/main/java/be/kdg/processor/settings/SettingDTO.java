package be.kdg.processor.settings;

import java.util.HashMap;
import java.util.Map;

public class SettingDTO {

    private Map<String, Integer> settings;

    public SettingDTO() {
        settings = new HashMap<>();
    }

    public SettingDTO(Map<String, Integer> settings) {
        this.settings = settings;
    }

    public Map<String, Integer> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, Integer> settings) {
        this.settings = settings;
    }
}
