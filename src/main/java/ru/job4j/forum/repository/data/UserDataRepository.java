package ru.job4j.forum.repository.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * 2. Spring boot security [#296071]
 * UserDataRepository spring data управление хранилищем моделей User.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.07.2022
 */
@Repository
public interface UserDataRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
