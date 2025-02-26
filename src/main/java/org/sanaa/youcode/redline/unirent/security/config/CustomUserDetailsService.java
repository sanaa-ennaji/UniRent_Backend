package org.sanaa.youcode.redline.unirent.security.config;

import lombok.RequiredArgsConstructor;
import org.sanaa.youcode.redline.unirent.model.entity.AppUser;
import org.sanaa.youcode.redline.unirent.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("user not found with email : " + email));

        return User.builder()
            .username(appUser.getEmail())
            .password(appUser.getPassword())
            .authorities(appUser.getRole().getRoleName())
            .build();
    }
}
