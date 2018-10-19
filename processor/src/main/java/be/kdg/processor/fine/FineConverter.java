package be.kdg.processor.fine;

import org.springframework.stereotype.Component;

@Component
public class FineConverter {
    public FineDTO convertFineToFineDTO(Fine fine) {
        return new FineDTO(fine.getOffense(), fine.getAmount());
    }
}
