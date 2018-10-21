package be.kdg.processor.fine;

import be.kdg.processor.offense.Offense;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@RestController
@RequestMapping("/api")
public class FineRestController {

    private final FineService fineService;
    private final ModelMapper modelMapper;
    private final DateTimeFormatter formatter;

    public FineRestController(FineService fineService, ModelMapper modelMapper) {
        this.fineService = fineService;
        this.modelMapper = modelMapper;
        formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    @GetMapping("/fines/{id}")
    public FineDTO readFine(@PathVariable Long id) throws FineException {
        Fine fine = fineService.load(id);
        return modelMapper.map(fine, FineDTO.class);
    }

    @GetMapping("/fines")
    public FineDTO[] readFines() {
        List<Fine> fines = fineService.getFines();
        return modelMapper.map(fines, FineDTO[].class);
    }

    @GetMapping("/fines/between/{from}/{till}")
    public FineDTO[] readFinesInInterval(@PathVariable String from, @PathVariable String till) {
        LocalDateTime dFrom = LocalDateTime.parse(from, formatter);
        LocalDateTime dTill = LocalDateTime.parse(till, formatter);
        List<Fine> fines = fineService.getFines();
        List<Fine> filteredFines = new ArrayList<>();
        for (Fine fine : fines) {
            if (fine.getOffense().getTimestamp().isAfter(dFrom) && fine.getOffense().getTimestamp().isBefore(dTill)) {
                filteredFines.add(fine);
            }
        }
        return modelMapper.map(filteredFines, FineDTO[].class);
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
