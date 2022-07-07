package ru.job4j.forum.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * Post модель данных поста на форуме.
 * 1. Spring boot repository [#2095]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private LocalDateTime created = LocalDateTime.now().withNano(0);

    public static Post of(String name, String description) {
        Post post = new Post();
        post.name = name;
        post.description = description;
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{id=" + id + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", created=" + created + '}';
    }
}
