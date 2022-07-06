package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.repository.mem.AuthorityRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * AuthorityService логика управления ролями пользователей.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 06.07.2022
 */
@Service
public class AuthorityService {
    private final AuthorityRepository authorities;

    public AuthorityService(AuthorityRepository authorities) {
        this.authorities = authorities;
    }

    public Optional<Authority> findByIdRole(int idRole) {
        return this.authorities.findById(idRole);
    }
}
