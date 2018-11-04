package be.kdg.processor.settings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, String> {
    Setting findByProperty(String property);
}
