package org.sanaa.youcode.redline.unirent.security.config;

import org.sanaa.youcode.redline.unirent.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/register").permitAll()
                .requestMatchers("/api/users/**").permitAll()
                .requestMatchers("/api/authenticate").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/properties/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/properties/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/properties/**").permitAll()
                .requestMatchers("/api/amenity/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/roles/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/roles/**").authenticated()
                .requestMatchers("/api/roles/**").permitAll()
                .requestMatchers("/api/universities/**").permitAll()
                .requestMatchers("/api/v1/booking/**").permitAll()
                .requestMatchers("/api/payments/**").permitAll()
                .anyRequest().permitAll()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
