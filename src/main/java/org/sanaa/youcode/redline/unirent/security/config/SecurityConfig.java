package org.sanaa.youcode.redline.unirent.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/roles/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/**").permitAll()
            .antMatchers(HttpMethod.PUT, "/api/**").permitAll()
            .antMatchers(HttpMethod.DELETE, "/api/**").permitAll()
            .anyRequest().authenticated();
        return http.build();
    }
}

