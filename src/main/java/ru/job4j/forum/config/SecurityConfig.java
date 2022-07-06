package ru.job4j.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.mem.UserMemRepository;
import ru.job4j.forum.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * SecurityConfig настройки авторизации Spring Boot.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 06.07.2022
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserMemRepository users = new UserMemRepository(passwordEncoder);
        for (User user : users.findAll()) {
            auth.inMemoryAuthentication()
                    .withUser(user.getUsername()).password(user.getPassword()).roles(user.getAuthority().getAuthority());
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/reg").permitAll()
                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and()
                .csrf()
                .disable();
    }
}
