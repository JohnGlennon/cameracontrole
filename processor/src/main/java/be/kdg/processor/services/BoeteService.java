package be.kdg.processor.services;

import be.kdg.processor.exceptions.BoeteException;
import be.kdg.processor.model.Boete;
import be.kdg.processor.persistence.BoeteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BoeteService {

    private final BoeteRepository boeteRepository;

    public BoeteService(BoeteRepository boeteRepository) {
        this.boeteRepository = boeteRepository;
    }

    public Boete save(Boete boete) {
        return boeteRepository.save(boete);
    }

    public Boete load(Long id) throws BoeteException {
        Optional<Boete> optionalBoete = boeteRepository.findById(id);
        if (optionalBoete.isPresent()) {
            return optionalBoete.get();
        }
        throw new BoeteException("Boete niet gevonden.");
    }
}
