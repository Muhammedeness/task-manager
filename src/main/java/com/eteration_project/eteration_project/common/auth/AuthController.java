package com.eteration_project.eteration_project.common.auth;

import com.eteration_project.eteration_project.common.auth.dto.LoginRequestDto;
import com.eteration_project.eteration_project.common.auth.dto.LoginResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService loginService;


    @PostMapping("/login")
   // @PreAuthorize("hasRole('USER')")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {

            LoginResponseDto response = loginService.login(loginRequest);
            return ResponseEntity.ok(response);

    }
} 