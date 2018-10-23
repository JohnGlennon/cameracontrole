package be.kdg.processor.fine;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FineFilter {
    public List<Fine> filterFineList(List<Fine> fines, LocalDateTime from, LocalDateTime till) {
        return fines.stream().filter(fine -> fine.getOffense().getTimestamp().isAfter(from) && fine.getOffense().getTimestamp().isBefore(till)).collect(Collectors.toList());
    }
}
