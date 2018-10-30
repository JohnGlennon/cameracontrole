package be.kdg.processor.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin")) {
            return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("admin123")
                    .roles("ADMIN")
                    .build();
        }
        throw new UsernameNotFoundException("Username not found.");
    }

    public User createAdmin(String username, String password) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles("ADMIN")
                .build();
        User user = new User(userDetails.getUsername(), userDetails.getPassword());
        save(user);
        return user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User load(Long id) throws UserException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserException("User not found.");
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User changePassword(String username, String newpassword) {
        User user = userRepository.findByUsername(username);
        user.setPassword(newpassword);
        return save(user);
    }
}
