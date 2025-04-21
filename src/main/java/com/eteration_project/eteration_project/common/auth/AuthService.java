package com.eteration_project.eteration_project.common.auth;

import com.eteration_project.eteration_project.common.token.JwtUtil;
import com.eteration_project.eteration_project.common.auth.dto.LoginRequestDto;
import com.eteration_project.eteration_project.common.auth.dto.LoginResponseDto;
import com.eteration_project.eteration_project.user.service.impl.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService userDetailService;
    private final JwtUtil jwtUtil;

    public LoginResponseDto login(LoginRequestDto loginRequest) {
       try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getEmail());
            String token = jwtUtil.generateToken(userDetails.getUsername());

            return new LoginResponseDto(token);
        }
         catch (AuthenticationException e) {
            throw new BadCredentialsException("Email ya da şifre hatalı");
        }
    }
}

