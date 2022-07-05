package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.mem.IRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * PostMemRepository хранилище моделей Post в памяти.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
@Service
public class PostService {
    private final IRepository<Post> posts;

    public PostService(final IRepository<Post> posts) {
        this.posts = posts;
    }

    public Post savePost(final Post post) {
        return this.posts.save(post);
    }

    public Optional<Post> findByIdPost(int postId) {
        return this.posts.findById(postId);
    }

    public Iterable<Post> findAllPost() {
        return this.posts.findAll();
    }
}
