package com.eteration_project.eteration_project.user.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDto {

    @NotBlank(message = "name.is.required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "check.name.field")
    @Size(min = 2  , max = 25 , message = "invalid.size")
    private String firstName;

    @NotBlank(message = "lastname.is.required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "check.name.field")
    @Size(min = 2 , max = 25 , message = "invalid.size")
    private String lastName;

    @Past(message = "invalid.birth.date")
    private Date birthDate;

    @NotBlank(message = "mail.is.required")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE , message = "invalid.mail")
    private String email;

    @NotBlank(message = "password.is.required")
    @Size(min = 4, message = "invalid.password.size")
    private String password;

}
