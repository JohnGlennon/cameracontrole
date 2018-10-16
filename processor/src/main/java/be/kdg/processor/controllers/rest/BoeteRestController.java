package be.kdg.processor.controllers.rest;

import be.kdg.processor.dto.BoeteDTO;
import be.kdg.processor.exceptions.BoeteException;
import be.kdg.processor.model.Boete;
import be.kdg.processor.model.Overtreding;
import be.kdg.processor.services.BoeteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BoeteRestController {

    private final BoeteService boeteService;
    private final ModelMapper modelMapper;

    public BoeteRestController(BoeteService boeteService, ModelMapper modelMapper) {
        this.boeteService = boeteService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/boetes/{id}")
    public ResponseEntity<BoeteDTO> loadBoete(@PathVariable Long id) throws BoeteException {
        Boete boete = boeteService.load(id);
        return new ResponseEntity<>(modelMapper.map(boete, BoeteDTO.class), HttpStatus.OK);
    }

    @PostMapping("/boetes")
    public ResponseEntity<BoeteDTO> createBoete(@RequestBody BoeteDTO boeteDTO) {
        Boete boete = boeteService.save(modelMapper.map(boeteDTO, Boete.class));
        return new ResponseEntity<>(modelMapper.map(boete, BoeteDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/boetes/{id}")
    public ResponseEntity<BoeteDTO> updateBoete(@PathVariable Long id, @RequestParam("overtreding") Overtreding newOvertreding, @RequestParam("bedrag") int newBedrag) throws BoeteException {
        Boete boeteIn = boeteService.load(id);
        boeteIn.setOvertreding(newOvertreding);
        boeteIn.setBedrag(newBedrag);
        Boete boeteOut = boeteService.save(boeteIn);
        return new ResponseEntity<>(modelMapper.map(boeteOut, BoeteDTO.class), HttpStatus.ACCEPTED);
    }
}
