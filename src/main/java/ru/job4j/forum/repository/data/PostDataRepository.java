package ru.job4j.forum.repository.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * 1. Spring boot repository [#2095 #319661]
 * PostDataRepository spring data управление хранилищем моделей Posts.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.07.2022
 */
@Repository
public interface PostDataRepository extends CrudRepository<Post, Integer> {
}
