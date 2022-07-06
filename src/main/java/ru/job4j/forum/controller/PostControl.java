package ru.job4j.forum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Optional;

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
    private static final Logger LOG = LoggerFactory.getLogger(PostControl.class.getSimpleName());
    private final PostService postService;

    public PostControl(PostService postService) {
        this.postService = postService;
    }

    /**
     * Отображение всех тем форума
     *
     * @param model Model
     * @return String
     */
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("posts", postService.findAllPost());
        return "post/index";
    }

    /**
     * Отображение деталей поста.
     *
     * @param model  Model
     * @param postId int
     * @return String
     */
    @GetMapping("/post/{id}")
    public String post(Model model, @PathVariable("id") int postId) {
        Optional<Post> post = postService.findByIdPost(postId);
        if (post.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("post", post.get());
        return "post/post";
    }

    /**
     * Отображение вида добавление новой темы форума.
     *
     * @param model Model
     * @return String
     */
    @GetMapping("/newPost")
    public String newPost(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("post", new Post());
        return "post/edit";
    }

    /**
     * Отображение вида редактирования поста.
     *
     * @param model  Model
     * @param postId int
     * @return String
     */
    @GetMapping("/editPost/{id}")
    public String editPost(Model model, @PathVariable("id") int postId) {
        Optional<Post> post = postService.findByIdPost(postId);
        if (post.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("post", post.get());
        return "post/edit";
    }

    /**
     * Метод пост сохраняет новую темы или обновляет существующую.
     *
     * @param post Post
     * @return String.
     */
    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post) {
        LOG.info(post.toString());
        postService.savePost(post);
        return "redirect:/";
    }
}
