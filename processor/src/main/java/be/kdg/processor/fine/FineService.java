package be.kdg.processor.fine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FineService {

    private final FineRepository fineRepository;
    private final LocalDateTimeConverter converter;

    public FineService(FineRepository fineRepository, LocalDateTimeConverter converter) {
        this.fineRepository = fineRepository;
        this.converter = converter;
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

    public List<Fine> getFines() {
        return fineRepository.findAll();
    }

    public List<Fine> getFinesInInterval(String from, String till) {
        LocalDateTime dFrom = converter.convertStringToLocalDateTime(from);
        LocalDateTime dTill = converter.convertStringToLocalDateTime(till);
        List<Fine> fines = fineRepository.findAll();
        return fines.stream().filter(fine -> fine.getOffense().getTimestamp().isAfter(dFrom) && fine.getOffense().getTimestamp().isBefore(dTill)).collect(Collectors.toList());
    }

    public Fine approveFine(Fine fineIn) throws FineException {
        Fine fineOut = load(fineIn.getId());
        fineOut.setApproved(true);
        return save(fineOut);
    }

    public Fine updateFine(Fine fineIn) throws FineException {
        Fine fineOut = load(fineIn.getId());
        fineOut.setAmount(fineIn.getAmount());
        fineOut.setMotivation(fineIn.getMotivation());
        return save(fineOut);
    }
}
