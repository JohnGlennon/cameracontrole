package be.kdg.processor.services;

import be.kdg.processor.exceptions.FineException;
import be.kdg.processor.model.Fine;
import be.kdg.processor.persistence.FineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class FineService {

    private final FineRepository fineRepository;

    public FineService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    public Fine save(Fine fine) {
        return fineRepository.save(fine);
    }

    public Fine load(Long id) throws FineException {
        Optional<Fine> optionalFine = fineRepository.findById(id);
        if (optionalFine.isPresent()) {
            return optionalFine.get();
        }
        throw new FineException("Fine not found.");
    }

    public void remove(Fine fine) {
        fineRepository.delete(fine);
    }
}
