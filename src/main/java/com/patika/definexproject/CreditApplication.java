package com.patika.definexproject;

import com.patika.definexproject.model.User;
import com.patika.definexproject.service.UserServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CreditApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CreditApplication.class, args);

    }
    @Bean
    CommandLineRunner createInitialUsers(UserServices userServices){
        return (args) -> {
                User user = new User();
                user.setUsername("user1");
                user.setEmail("k.metin001@gmail.com");
                user.setPassword("E2Kerem01.");
                userServices.save(user);
            };

    }

}
