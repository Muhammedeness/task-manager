package com.eteration_project.eteration_project.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AssignUserDto {

    @NotBlank(message = "project.name.is.required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "check.project.name.field")
    @Size(min = 2  , max = 25 , message = "invalid.size")
    private String  projectName;

    @NotBlank(message = "name.is.required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "check.name.field")
    @Size(min = 2  , max = 25 , message = "invalid.size")
    private String  firstName;

    @NotBlank(message = "lastname.is.required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "check.name.field")
    @Size(min = 2  , max = 25 , message = "invalid.size")
    private  String lastName;

    @NotBlank(message = "mail.is.required")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE , message = "invalid.mail")
    private String email;
}
