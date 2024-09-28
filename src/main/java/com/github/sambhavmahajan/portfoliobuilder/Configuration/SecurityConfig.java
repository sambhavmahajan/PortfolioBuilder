package com.github.sambhavmahajan.portfoliobuilder.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/view/**").permitAll()
                .requestMatchers("/register/**").permitAll()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/profile").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}
