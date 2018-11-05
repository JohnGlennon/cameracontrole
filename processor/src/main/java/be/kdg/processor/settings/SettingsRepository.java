package be.kdg.processor.settings;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
    Settings findBySettings(Map<String, Integer> settings);
}
