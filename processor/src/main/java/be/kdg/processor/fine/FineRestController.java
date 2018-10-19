package be.kdg.processor.fine;

import be.kdg.processor.offense.Offense;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/fines")
    public FineDTO[] readFines() {
        List<Fine> fines = fineService.getFines();
        return modelMapper.map(fines, FineDTO[].class);
    }

    @PostMapping("/fines/create")
    public ResponseEntity<FineDTO> createFine(@RequestBody FineDTO fineDTO) {
        Fine fine = fineService.save(modelMapper.map(fineDTO, Fine.class));
        return new ResponseEntity<>(modelMapper.map(fine, FineDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/fines/update/{id}")
    public ResponseEntity<FineDTO> updateFine(@PathVariable Long id, @RequestParam("offense") Offense newOffense, @RequestParam("amount") int newAmount) throws FineException {
        Fine fineIn = fineService.load(id);
        fineIn.setOffense(newOffense);
        fineIn.setAmount(newAmount);
        Fine fineOut = fineService.save(fineIn);
        return new ResponseEntity<>(modelMapper.map(fineOut, FineDTO.class), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/fines/delete/{id}")
    public ResponseEntity<FineDTO> deleteFine(@PathVariable Long id) throws FineException {
        Fine fine = fineService.load(id);
        fineService.remove(fine);
        return new ResponseEntity<>(modelMapper.map(fine, FineDTO.class), HttpStatus.GONE);
    }
}
