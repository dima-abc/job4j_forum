package ru.job4j.forum.repository.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * 2. Spring boot security [#296071]
 * AuthorityDataRepository spring data управление хранилищем моделей Authority.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.07.2022
 */
@Repository
public interface AuthorityDataRepository extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
