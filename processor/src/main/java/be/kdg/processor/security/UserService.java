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
        User user = userRepository.findByUsername(username);
        return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username(username)
                .password(user.getPassword())
                .roles("ADMIN")
                .build();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User changePassword(User userIn) {
        User userOut = userRepository.findByUsername(userIn.getUsername());
        userOut.setPassword(userIn.getPassword());
        return save(userOut);
    }

    public User deleteUser(User userIn) {
        User userOut = userRepository.findByUsername(userIn.getUsername());
        userRepository.delete(userOut);
        return userOut;
    }
}
