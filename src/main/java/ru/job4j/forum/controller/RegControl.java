package ru.job4j.forum.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AuthorityService;
import ru.job4j.forum.service.UserService;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * RegControl контроллер регистрации нового пользователя.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 06.07.2022
 */
@Controller
public class RegControl {
    private final PasswordEncoder encoder;
    private final UserService users;
    private final AuthorityService authorities;

    public RegControl(PasswordEncoder encoder, UserService users, AuthorityService authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setAuthority(authorities.findByIdRole(2).get());
        user.setPassword(encoder.encode(user.getPassword()));
        users.saveUser(user);
        return "redirect:/login";
    }

    /**
     * Отображение вида reg.html регистрации нового пользователя.
     *
     * @param model Model.
     * @param error String
     * @return String.
     */
    @GetMapping("/reg")
    public String regPage(Model model,
                          @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Имя пользователя уже существует!!!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "user/reg";
    }
}
