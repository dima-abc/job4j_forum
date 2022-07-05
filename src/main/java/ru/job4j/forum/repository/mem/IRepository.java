package ru.job4j.forum.repository.mem;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * IRepository основные операции управления хранилищем.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 05.07.2022
 */
public interface IRepository<T> {
    T save(T type);

    Optional<T> findById(int id);

    Iterable<T> findAll();
}
