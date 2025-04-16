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

    @NotBlank(message = "İsim alanı boş bırakılamaz")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "İsim Alanın Lütfen Kontrol Ediniz")
    @Size(min = 2  , max = 25 , message = "Fazla veya az karakter kullanıldı")
    private String firstName;

    @NotBlank(message = "Soyisim alanı boş bırakılamaz")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Soyisim Alanını Lütfen Kontrol Ediniz")
    @Size(min = 2 , max = 25 , message = "Fazla veya az karakter kullanıldı")
    private String lastName;

    @Past(message = "Doğum Tarihi Gelecekte ki bir tarih olarak girildi")
    private Date birthDate;

    @NotBlank(message = "Mail alanı boş bırakılamaz")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE , message = "Geçerli Bİr Mail Adresi Giriniz")
    private String email;

    @NotBlank(message = "İsim alanı boş bırakılamaz")
    @Size(min = 4   , message = "Minimum 4  karakter olmalı")
    private String password;

}
