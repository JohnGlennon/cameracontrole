package be.kdg.processor.services;

import be.kdg.processor.exceptions.FineException;
import be.kdg.processor.model.Fine;
import be.kdg.processor.persistence.FineRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class FineService {

    @Value("${emissionfactor}")
    private int emissionfactor;

    @Value("${speedfactor}")
    private int speedfactor;

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

    public int getEmissionfactor() {
        return emissionfactor;
    }

    public void setEmissionfactor(int emissionfactor) {
        this.emissionfactor = emissionfactor;
    }

    public int getSpeedfactor() {
        return speedfactor;
    }

    public void setSpeedfactor(int speedfactor) {
        this.speedfactor = speedfactor;
    }
}
