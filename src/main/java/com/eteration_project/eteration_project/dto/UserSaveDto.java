package com.eteration_project.eteration_project.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class UserSaveDto {

    @NotEmpty(message = "İsim alanı boş bırakılamaz")
    @Size(min = 2  , max = 25 , message = "Fazla veya az karakter kullanıldı")
    private String firstName;

    @NotEmpty(message = "Soyisim alanı boş bırakılamaz")
    @Size(min = 2 , max = 25 , message = "Fazla veya az karakter kullanıldı")
    private String lastName;

    private Date birthDate;

    private String email;

}
