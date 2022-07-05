package ru.job4j.forum.repository.mem;

import ru.job4j.forum.model.User;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * UserMemRepository моделей User в памяти.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 05.07.2022
 */
public class UserMemRepository implements IRepository<User> {
    @Override
    public User save(User type) {
        return null;
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }
}
