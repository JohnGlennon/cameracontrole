package be.kdg.processor.persistence;

import be.kdg.processor.model.Boete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoeteRepository extends JpaRepository<Boete, Long> {
    List<Boete> findByBedrag(int bedrag);
}
