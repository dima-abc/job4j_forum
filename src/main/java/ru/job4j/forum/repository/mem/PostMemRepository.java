package ru.job4j.forum.repository.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * PostMemRepository хранилище моделей Post в памяти.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
public class PostMemRepository implements IRepository<Post> {
    private final AtomicInteger key = new AtomicInteger();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    public PostMemRepository() {
        for (int i = 0; i < 5; i++) {
            Post post =  Post.of("post №" + i, "description post #" + i);
            post.setId(key.incrementAndGet());
            this.posts.put(post.getId(), post);
        }
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(key.incrementAndGet());
        }
        return this.posts.merge(post.getId(), post,
                (p1, p2) -> Post.of(p2.getName(), p2.getDescription()));
    }

    @Override
    public Optional<Post> findById(int id) {
        return Optional.ofNullable(this.posts.get(id));
    }

    @Override
    public Iterable<Post> findAll() {
        return new ArrayList<>(this.posts.values());
    }
}
