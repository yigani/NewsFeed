package com.example.NewsFeed.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

            return new BCryptPasswordEncoder();

    }

}
