package be.kdg.processor.fine;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FineService {

    private int emissionfactor = 50;
    private int speedfactor = 50;

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

    public int getEmissionfactor() {
        return emissionfactor;
    }

    public int getSpeedfactor() {
        return speedfactor;
    }

    public void setEmissionfactor(int emissionfactor) {
        this.emissionfactor = emissionfactor;
    }

    public void setSpeedfactor(int speedfactor) {
        this.speedfactor = speedfactor;
    }

    public List<Fine> getFines() {
        return fineRepository.findAll();
    }

    public Fine approveFine(Long id, boolean approved) throws FineException {
        Fine fineIn = load(id);
        fineIn.setApproved(approved);
        return save(fineIn);
    }

    public Fine updateFine(Long id, double amount, String motivation) throws FineException {
        Fine fineIn = load(id);
        fineIn.setAmount(amount);
        fineIn.setMotivation(motivation);
        return save(fineIn);
    }
}
