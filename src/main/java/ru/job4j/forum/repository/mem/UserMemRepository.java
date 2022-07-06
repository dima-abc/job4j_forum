package ru.job4j.forum.repository.mem;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * UserMemRepository моделей User в памяти.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 05.07.2022
 */
@Repository
public class UserMemRepository implements IRepository<User> {
    private final AtomicInteger key = new AtomicInteger();
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    public UserMemRepository(PasswordEncoder passwordEncoder) {
        Authority rootRole = Authority.of("ADMIN");
        rootRole.setId(0);
        Authority userRole = Authority.of("USER");
        userRole.setId(1);
        User root = User.of("admin", passwordEncoder.encode("root"), rootRole);
        root.setId(key.incrementAndGet());
        User user = User.of("user", passwordEncoder.encode("user"), userRole);
        user.setId(key.incrementAndGet());
        users.put(root.getId(), root);
        users.put(user.getId(), user);
    }

    @Override
    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(key.incrementAndGet());
        }
        return this.users.merge(user.getId(), user, (u1, u2) -> u2);
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Iterable<User> findAll() {
        return new ArrayList<>(this.users.values());
    }
}
