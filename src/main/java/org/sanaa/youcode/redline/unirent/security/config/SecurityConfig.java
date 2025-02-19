package org.sanaa.youcode.redline.unirent.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/roles/**").permitAll()
                .requestMatchers("/api/users/**").permitAll()
                .requestMatchers("/api/universities/**").permitAll()
                .requestMatchers("/api/properties/**").permitAll()
                .requestMatchers("/api/amenity/**").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }
}


