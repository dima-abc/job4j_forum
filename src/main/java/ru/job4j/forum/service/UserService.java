package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.mem.IRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * UserService слой бизнес логики управления моделью User.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 06.07.2022
 */
@Service
public class UserService {
    private final IRepository<User> users;

    public UserService(IRepository<User> users) {
        this.users = users;
    }

    public User saveUser(User user) {
        return this.users.save(user);
    }

    public Optional<User> findByIdUser(int userId) {
        return this.users.findById(userId);
    }

    public Iterable<User> findAllUser() {
        return this.users.findAll();
    }
}
