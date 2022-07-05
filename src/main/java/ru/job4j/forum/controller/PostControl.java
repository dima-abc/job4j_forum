package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * PostControl контроллер управлениями видов модели Post.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 05.07.2022
 */
@Controller
public class PostControl {
    private final PostService postService;

    public PostControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", postService.findAllPost());
        return "post/index";
    }
}
