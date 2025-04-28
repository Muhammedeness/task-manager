package com.eteration_project.eteration_project.auth.controller;

import com.eteration_project.eteration_project.auth.dto.LoginRequestDto;
import com.eteration_project.eteration_project.auth.dto.LoginResponseDto;
import com.eteration_project.eteration_project.auth.dto.TokenInspectDto;
import com.eteration_project.eteration_project.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService loginService;


    @PostMapping("/login")
   // @PreAuthorize("hasRole('USER')")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequest) {

            LoginResponseDto response = loginService.login(loginRequest);
            return ResponseEntity.ok(response);

    }

    @PostMapping(path = "/inspect")
    public ResponseEntity<TokenInspectDto> inspect(@RequestParam("token") String token) {
        return ResponseEntity.ok(loginService.inspectToken(token));
    }

} 