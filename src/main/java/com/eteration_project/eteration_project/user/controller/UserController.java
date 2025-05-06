package com.eteration_project.eteration_project.user.controller;

import com.eteration_project.eteration_project.keycloak.service.KeycloakService;
import com.eteration_project.eteration_project.user.dto.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {


    private final KeycloakService keycloakService;

    @PostMapping("/get-user")
    public UserDetailsDTO getUserDetails(@RequestParam String userEmail){

        return keycloakService.getUserDetails(userEmail);
    }
}
