package com.eteration_project.eteration_project.user.service.impl;

import com.eteration_project.eteration_project.user.model.User;
import com.eteration_project.eteration_project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //User user = userRepository.findUserByEmail(email);
        User user= userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), //burası dogru email dönüyor
                user.getPassword(), //null döndürüyor
                Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
    }
}

