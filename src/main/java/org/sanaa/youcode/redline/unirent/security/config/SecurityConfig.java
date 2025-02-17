package org.sanaa.youcode.redline.unirent.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .dispatcherTypeMatchers("/api/roles/**").permitAll()
            .dispatcherTypeMatchers(HttpMethod.GET, "/api/**").permitAll()
            .dispatcherTypeMatchers(HttpMethod.POST, "/api/**").permitAll()
            .dispatcherTypeMatchers(HttpMethod.PUT, "/api/**").permitAll()
            .dispatcherTypeMatchers(HttpMethod.DELETE, "/api/**").permitAll()
            .anyRequest().authenticated();
    }
}
