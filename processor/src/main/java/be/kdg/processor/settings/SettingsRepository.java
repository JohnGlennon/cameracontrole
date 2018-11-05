package be.kdg.processor.settings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
    Settings findByProperty(String property);
}
