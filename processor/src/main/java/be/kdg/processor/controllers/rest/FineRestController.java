package be.kdg.processor.controllers.rest;

import be.kdg.processor.dto.FineDTO;
import be.kdg.processor.exceptions.FineException;
import be.kdg.processor.model.Fine;
import be.kdg.processor.model.Offense;
import be.kdg.processor.services.FineService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FineRestController {

    private final FineService fineService;
    private final ModelMapper modelMapper;

    public FineRestController(FineService fineService, ModelMapper modelMapper) {
        this.fineService = fineService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/fines/{id}")
    public ResponseEntity<FineDTO> readFine(@PathVariable Long id) throws FineException {
        Fine fine = fineService.load(id);
        return new ResponseEntity<>(modelMapper.map(fine, FineDTO.class), HttpStatus.OK);
    }

    @PostMapping("/fines")
    public ResponseEntity<FineDTO> createFine(@RequestBody FineDTO fineDTO) {
        Fine fine = fineService.save(modelMapper.map(fineDTO, Fine.class));
        return new ResponseEntity<>(modelMapper.map(fine, FineDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/fines/{id}")
    public ResponseEntity<FineDTO> updateFine(@PathVariable Long id, @RequestParam("offense") Offense newOffense, @RequestParam("amount") int newAmount) throws FineException {
        Fine fineIn = fineService.load(id);
        fineIn.setOffense(newOffense);
        fineIn.setAmount(newAmount);
        Fine fineOut = fineService.save(fineIn);
        return new ResponseEntity<>(modelMapper.map(fineOut, FineDTO.class), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/fines/{id}")
    public ResponseEntity<FineDTO> deleteFine(@PathVariable Long id) throws FineException {
        Fine fine = fineService.load(id);
        fineService.remove(fine);
        return new ResponseEntity<>(modelMapper.map(fine, FineDTO.class), HttpStatus.GONE);
    }
}
