package be.kdg.processor.settings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Long> {
    Setting findByProperty(String property);
}
