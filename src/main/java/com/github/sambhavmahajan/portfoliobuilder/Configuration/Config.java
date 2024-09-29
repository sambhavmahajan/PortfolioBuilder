package com.github.sambhavmahajan.portfoliobuilder.Configuration;

import com.github.sambhavmahajan.portfoliobuilder.Repository.UserRepository;
import com.github.sambhavmahajan.portfoliobuilder.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {
    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
         return new UserService(userRepository, passwordEncoder);
    }
}
