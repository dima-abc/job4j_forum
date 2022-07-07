package ru.job4j.forum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.data.UserDataRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * 2. Spring boot security [#296071]
 * UserService слой бизнес логики управления моделью User.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 06.07.2022
 */
@Service
public class UserService implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class.getSimpleName());
    private final UserDataRepository users;

    public UserService(UserDataRepository users) {
        this.users = users;
    }

    public Optional<User> saveUser(User user) {
        Optional<User> result = Optional.empty();
        try {
            result = Optional.of(users.save(user));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return result;
    }

    public Optional<User> findByIdUser(int userId) {
        return this.users.findById(userId);
    }

    public Iterable<User> findAllUser() {
        return this.users.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = users.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
        return user.get();
    }
}
