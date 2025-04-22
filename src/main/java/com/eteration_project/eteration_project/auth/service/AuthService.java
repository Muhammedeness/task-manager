package com.eteration_project.eteration_project.auth.service;

import com.eteration_project.eteration_project.common.security.token.JwtUtil;
import com.eteration_project.eteration_project.auth.dto.LoginRequestDto;
import com.eteration_project.eteration_project.auth.dto.LoginResponseDto;
import com.eteration_project.eteration_project.user.service.impl.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService userDetailService;
    private final JwtUtil jwtUtil;
    private static final Logger logger =  LoggerFactory.getLogger(AuthService.class);

    public LoginResponseDto login(LoginRequestDto loginRequest) {
       try {
         Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
           UserDetails userDetails = (UserDetails) authentication.getPrincipal();
           System.out.println("Auth Provider: " + authenticationManager.getClass());
            String token = jwtUtil.generateToken(userDetails.getUsername());
            Date expiredDate = jwtUtil.getExpiration(token);
            String email = jwtUtil.extractEmail(token);
            return new LoginResponseDto(token , expiredDate , email);
       }
       catch (BadCredentialsException e) {
           logger.warn("AuthService username or password invalid : {}", e.getMessage());                 //Hata mesajı logger araciligi ile loglandı

           throw  new BadCredentialsException("invalid.email.or.password.security");   //message.properties  dosyasındandan
                                                                                       // bu key ile bilgilendirme mesajı gönderilir
        }
    }
}

