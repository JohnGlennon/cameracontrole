package be.kdg.processor.fine.controllers;

import be.kdg.processor.fine.dto.FineDTO;
import be.kdg.processor.fine.FineException;
import be.kdg.processor.fine.LocalDateTimeConverter;
import be.kdg.processor.fine.Fine;
import be.kdg.processor.fine.FineService;
import be.kdg.processor.security.User;
import be.kdg.processor.security.UserDTO;
import be.kdg.processor.security.UserException;
import be.kdg.processor.security.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FineRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(FineRestController.class);

    private final FineService fineService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public FineRestController(FineService fineService, ModelMapper modelMapper, UserService userService) {
        this.fineService = fineService;
        this.modelMapper = modelMapper;
        this.userService = userService;
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
        List<Fine> fines = fineService.getFinesInInterval(from, till);
        return modelMapper.map(fines, FineDTO[].class);
    }

    @GetMapping("/fines/approve/{id}/{approved}")
    public ResponseEntity<FineDTO> approveFine(@PathVariable Long id, @PathVariable boolean approved) {
        try {
            Fine fine = fineService.approveFine(id, approved);
            return new ResponseEntity<>(modelMapper.map(fine, FineDTO.class), HttpStatus.ACCEPTED);
        } catch (FineException e) {
            LOGGER.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/fines/update/{id}/{amount}/{motivation}")
    public ResponseEntity<FineDTO> updateAmount(@PathVariable Long id, @PathVariable double amount, @PathVariable String motivation) {
        try {
            Fine fine = fineService.updateFine(id, amount, motivation);
            return new ResponseEntity<>(modelMapper.map(fine, FineDTO.class), HttpStatus.ACCEPTED);
        } catch (FineException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/createadmin/{username}/{password}")
    public ResponseEntity<UserDTO> createAdmin(@PathVariable String username, @PathVariable String password) {
        User userIn = userService.createAdmin(username, password);
        User userOut = userService.save(userIn);
        return new ResponseEntity<>(modelMapper.map(userOut, UserDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/readadmins")
    public UserDTO[] readAdmins() {
        List<User> users = userService.getUsers();
        return modelMapper.map(users, UserDTO[].class);
    }

    @GetMapping("/changepassword/{username}/{oldpassword}/{newpassword}")
    public ResponseEntity<UserDTO> changePassword(@PathVariable String username, @PathVariable String oldPassword, @PathVariable String newPassword) {
        try {
            User user = userService.changePassword(username, oldPassword, newPassword);
            return new ResponseEntity<>(modelMapper.map(user, UserDTO.class), HttpStatus.ACCEPTED);
        } catch (UserException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
