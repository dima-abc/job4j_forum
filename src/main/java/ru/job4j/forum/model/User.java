package ru.job4j.forum.model;

import java.util.Objects;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * User модель данных пользователя форума.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
public class User {
    private int id;
    private String username;
    private String password;
    private Authority authority;

    public static User of(String username, String password, Authority authority) {
        User user = new User();
        user.username = username;
        user.password = password;
        user.authority = authority;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
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
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + '\''
                + ", password='" + password + '\'' + ", authority=" + authority + '}';
    }
}
