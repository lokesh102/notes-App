package com.notesApp.notes.App.NotesApp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.awt.*;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
//                                .requestMatchers("/products").hasAuthority("ADMIN")
//                                .anyRequest().authenticated() //only allow a person who has logged in to be able to access any URL
                                .requestMatchers("/api/notes").hasAuthority("ADMIN")
                                .anyRequest().authenticated() // allow anyone to access any url without needing login

                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(new CustomJwtAuthenticationConverter())
                        )

                );
//                .cors().disable()
//                .csrf().disable();
        return http.build();
    }


}