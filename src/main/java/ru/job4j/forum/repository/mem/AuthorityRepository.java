package ru.job4j.forum.repository.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * AuthorityRepository хранилище ролей пользователя Authority
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 06.07.2022
 */
public class AuthorityRepository implements IRepository<Authority> {
    private final AtomicInteger key = new AtomicInteger();
    private final Map<Integer, Authority> authorities = new ConcurrentHashMap<>();

    public AuthorityRepository() {
        Authority root = Authority.of("ADMIN");
        root.setId(key.incrementAndGet());
        Authority user = Authority.of("USER");
        user.setId(key.incrementAndGet());
        this.authorities.put(root.getId(), root);
        this.authorities.put(user.getId(), user);
    }

    @Override
    public Authority save(Authority authority) {
        if (authority.getId() == 0) {
            authority.setId(key.incrementAndGet());
        }
        return this.authorities.merge(authority.getId(), authority, (a1, a2) -> a2);
    }

    @Override
    public Optional<Authority> findById(int id) {
        return Optional.ofNullable(this.authorities.get(id));
    }

    @Override
    public Iterable<Authority> findAll() {
        return new ArrayList<>(this.authorities.values());
    }
}
