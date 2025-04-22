package com.eteration_project.eteration_project.common.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDto {

   @NotBlank(message = "mail.is.required")
   @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
           flags = Pattern.Flag.CASE_INSENSITIVE , message = "invalid.mail")
   private String email;

   @NotBlank(message = "password.is.required")
   @Size(min = 4, message = "invalid.password.size")
   private  String password;
}
