package com.eteration_project.eteration_project.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.SecureRandom;
import java.util.Date;

@Data
@AllArgsConstructor
public class LoginResponseDto {

    private String token;
    private Date expiredDate;
    private String email;
    private String userName;
}
