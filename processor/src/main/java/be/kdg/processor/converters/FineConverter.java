package be.kdg.processor.converters;

import be.kdg.processor.dto.FineDTO;
import be.kdg.processor.model.Fine;
import org.springframework.stereotype.Component;

@Component
public class FineConverter {
    public FineDTO convertFineToFineDTO(Fine fine) {
        return new FineDTO(fine.getOffense(), fine.getAmount());
    }
}
