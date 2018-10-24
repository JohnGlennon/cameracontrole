package be.kdg.processor.fine;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FineRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(FineRestController.class);

    private final FineService fineService;
    private final ModelMapper modelMapper;
    private final LocalDateTimeConverter converter;
    private final FineFilter fineFilter;

    public FineRestController(FineService fineService, ModelMapper modelMapper, LocalDateTimeConverter converter, FineFilter fineFilter) {
        this.fineService = fineService;
        this.modelMapper = modelMapper;
        this.converter = converter;
        this.fineFilter = fineFilter;
    }

    @GetMapping("/fines/{id}")
    public FineDTO readFine(@PathVariable Long id) {
        try {
            Fine fine = fineService.load(id);
            return modelMapper.map(fine, FineDTO.class);
        } catch (FineException e) {
            LOGGER.error(e.getMessage());
        }
        return new FineDTO();
    }

    @GetMapping("/fines")
    public FineDTO[] readFines() {
        List<Fine> fines = fineService.getFines();
        return modelMapper.map(fines, FineDTO[].class);
    }

    @GetMapping("/fines/between/{from}/{till}")
    public FineDTO[] readFinesInInterval(@PathVariable String from, @PathVariable String till) {
        LocalDateTime dFrom = converter.convertStringToLocalDateTime(from);
        LocalDateTime dTill = converter.convertStringToLocalDateTime(till);
        List<Fine> fines = fineService.getFines();
        List<Fine> filteredFines = fineFilter.filterFineList(fines, dFrom, dTill);
        return modelMapper.map(filteredFines, FineDTO[].class);
    }

    @GetMapping("/fines/approve/{id}/{approved}")
    public ResponseEntity<FineDTO> approveFine(@PathVariable Long id, @PathVariable boolean approved) {
        try {
            Fine fineIn = fineService.load(id);
            fineIn.setApproved(approved);
            Fine fineOut = fineService.save(fineIn);
            return new ResponseEntity<>(modelMapper.map(fineOut, FineDTO.class), HttpStatus.ACCEPTED);
        } catch (FineException e) {
            LOGGER.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/fines/update/{id}/{amount}/{motivation}")
    public ResponseEntity<FineDTO> updateAmount(@PathVariable Long id, @PathVariable int amount, @PathVariable String motivation) {
        try {
            Fine fineIn = fineService.load(id);
            fineIn.setAmount(amount);
            fineIn.setMotivation(motivation);
            Fine fineOut = fineService.save(fineIn);
            return new ResponseEntity<>(modelMapper.map(fineOut, FineDTO.class), HttpStatus.ACCEPTED);
        } catch (FineException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
