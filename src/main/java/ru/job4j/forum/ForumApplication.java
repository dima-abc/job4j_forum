package ru.job4j.forum;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.5. Boot
 * 0. Spring Liquibase [#302330]
 * ForumApplication start application.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
@SpringBootApplication
public class ForumApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ForumApplication.class);
    }

    @Bean
    public SpringLiquibase liquibase(DataSource ds) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        liquibase.setDataSource(ds);
        return liquibase;
    }

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
        System.out.println("Go to url http://localhost:8080");
    }
}
