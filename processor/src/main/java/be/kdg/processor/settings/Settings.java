package be.kdg.processor.settings;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
public class Settings {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private Map<String, Integer> settings;

    public Settings() {
        settings = new HashMap<>();
    }

    public Settings(Map<String, Integer> settings) {
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

    public int getEmissionFactor() {
        return settings.get("emissionFactor");
    }

    public int getSpeedFactor() {
        return settings.get("speedFactor");
    }

    public int getEmissionTimeframe() {
        return settings.get("emissionTimeframe");
    }

    public int getSpeedTimeframe() {
        return settings.get("speedTimeframe");
    }
}
