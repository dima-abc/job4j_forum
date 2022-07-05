package ru.job4j.forum.repository.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
@Repository
public class PostMemRepository {
    private final AtomicInteger key = new AtomicInteger();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    public PostMemRepository() {
        for (int i = 0; i < 5; i++) {
            this.posts.put(key.incrementAndGet(), Post.of("post №" + i, "description post #" + i));
        }
    }

    public Post save(Post post) {
        post.setId(key.incrementAndGet());
        return this.posts.putIfAbsent(post.getId(), post);
    }

    public Post update(Post post) {
        return this.posts.replace(post.getId(), post);
    }

    public Post findById(int id) {
        return this.posts.get(id);
    }
}
