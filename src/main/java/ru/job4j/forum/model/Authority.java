package ru.job4j.forum.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * Authority модель данных ролей пользователей.
 * 2. Spring boot security [#296071]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String authority;

    public static Authority of(String authority) {
        Authority result = new Authority();
        result.authority = authority;
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authority authority1 = (Authority) o;
        return id == authority1.id && Objects.equals(authority, authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }

    @Override
    public String toString() {
        return "Authority{id=" + id + ", authority='" + authority + '\'' + '}';
    }
}
