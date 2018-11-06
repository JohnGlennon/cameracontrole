package be.kdg.processor.security;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/createadmin")
    public ResponseEntity<UserDTO> createAdmin(@RequestBody UserDTO userDTO) {
        User userIn = modelMapper.map(userDTO, User.class);
        User userOut = userService.save(userIn);
        return new ResponseEntity<>(modelMapper.map(userOut, UserDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/readadmins")
    public UserDTO[] readAdmins() {
        List<User> users = userService.getUsers();
        return modelMapper.map(users, UserDTO[].class);
    }

    @PutMapping("/changepassword")
    public ResponseEntity<UserDTO> changePassword(@RequestBody UserDTO userDTO) {
        User userIn = modelMapper.map(userDTO, User.class);
        User userOut = userService.changePassword(userIn);
        return new ResponseEntity<>(modelMapper.map(userOut, UserDTO.class), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteadmin")
    public ResponseEntity<UserDTO> deleteAdmin(@RequestBody UserDTO userDTO) {
        User userIn = modelMapper.map(userDTO, User.class);
        User userOut = userService.deleteUser(userIn);
        return new ResponseEntity<>(modelMapper.map(userOut, UserDTO.class), HttpStatus.OK);
    }
}
