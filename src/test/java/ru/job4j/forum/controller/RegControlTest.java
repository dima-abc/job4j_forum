package ru.job4j.forum.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.ForumApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * 0. Spring test [#6881]
 * RegControl TEST GET методы
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 08.07.2022
 */
@SpringBootTest(classes = ForumApplication.class)
@AutoConfigureMockMvc
class RegControlTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void regPage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/reg"));
    }
}