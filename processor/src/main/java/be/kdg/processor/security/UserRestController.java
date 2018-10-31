package be.kdg.processor.security;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/createadmin/{username}/{password}")
    public ResponseEntity<UserDTO> createAdmin(@PathVariable String username, @PathVariable String password) {
        User user = userService.createAdmin(username, password);
        return new ResponseEntity<>(modelMapper.map(user, UserDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/readadmins")
    public UserDTO[] readAdmins() {
        List<User> users = userService.getUsers();
        return modelMapper.map(users, UserDTO[].class);
    }

    @GetMapping("/changepassword/{username}/{oldpassword}/{newpassword}")
    public ResponseEntity<UserDTO> changePassword(@PathVariable String username, @PathVariable String oldpassword, @PathVariable String newpassword) {
        User user = userService.changePassword(username, oldpassword, newpassword);
        return new ResponseEntity<>(modelMapper.map(user, UserDTO.class), HttpStatus.ACCEPTED);
    }

    @GetMapping("deleteadmin/{username}")
    public ResponseEntity<UserDTO> deleteAdmin(@PathVariable String username) {
        User user = userService.deleteUser(username);
        return new ResponseEntity<>(modelMapper.map(user, UserDTO.class), HttpStatus.ACCEPTED);
    }
}
