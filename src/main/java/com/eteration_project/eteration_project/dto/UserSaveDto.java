package com.eteration_project.eteration_project.dto;

import jakarta.persistence.Column;
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

    @NotEmpty(message = "İsim alanı boş bırakılamaz")
    @Size(min = 2  , max = 25 , message = "Fazla veya az karakter kullanıldı")
    private String firstName;

    @NotEmpty(message = "Soyisim alanı boş bırakılamaz")
    @Size(min = 2 , max = 25 , message = "Fazla veya az karakter kullanıldı")
    private String lastName;

    @Past(message = "Doğum Tarihi Gelecekte ki bir tarih olarak girildi")
    private Date birthDate;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

}
