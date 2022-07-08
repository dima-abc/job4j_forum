package ru.job4j.forum.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.ForumApplication;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * 0. Spring test [#6881]
 * PostControl TEST GET методы.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.07.2022
 */
@SpringBootTest(classes = ForumApplication.class)
@AutoConfigureMockMvc
class PostControlTest {
    @MockBean
    private PostService posts;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/index"));
    }

    @Test
    @WithMockUser
    void postGetOk() throws Exception {
        Post post = new Post();
        post.setId(1);
        when(posts.findByIdPost(post.getId())).thenReturn(Optional.of(post));
        this.mockMvc.perform(get("/post/" + post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/post"));
    }

    @Test
    @WithMockUser
    void postGetFail() throws Exception {
        this.mockMvc.perform(get("/post/-1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @WithMockUser
    void newPost() throws Exception {
        this.mockMvc.perform(get("/newPost"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/edit"));
    }

    @Test
    @WithMockUser
    void editPostOk() throws Exception {
        Post post = new Post();
        post.setId(1);
        when(posts.findByIdPost(post.getId())).thenReturn(Optional.of(post));
        this.mockMvc.perform(get("/editPost/" + post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/edit"));
    }

    @Test
    @WithMockUser
    void editPostFail() throws Exception {
        this.mockMvc.perform(get("/editPost/-1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @WithMockUser
    void savePost() throws Exception {
        this.mockMvc.perform(post("/save")
                .param("name", "Куплю ладу-гранту."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).savePost(argument.capture());
        assertEquals(argument.getValue().getName(), "Куплю ладу-гранту.");
    }
}