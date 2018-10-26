package be.kdg.processor.fine;

import be.kdg.processor.fine.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Long> {
    List<Fine> findByAmount(double amount);
}
