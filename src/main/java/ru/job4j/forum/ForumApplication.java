package ru.job4j.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
@SpringBootApplication
public class ForumApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
        System.out.println("Go to url http://localhost:8080");
    }
}
