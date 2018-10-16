package be.kdg.processor.persistence;

import be.kdg.processor.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Long> {
    List<Fine> findByAmount(int amount);
}
