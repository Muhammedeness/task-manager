package com.eteration_project.eteration_project.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignUserDto {

    @NotBlank(message = "İsim alanı boş bırakılamaz")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "İsim Alanın Lütfen Kontrol Ediniz")
    @Size(min = 2  , max = 25 , message = "Fazla veya az karakter kullanıldı")
    private String  projectName;

    @NotBlank(message = "İsim alanı boş bırakılamaz")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "İsim Alanın Lütfen Kontrol Ediniz")
    @Size(min = 2  , max = 25 , message = "Fazla veya az karakter kullanıldı")
    private String  userName;

    @NotBlank(message = "Soy İsm alanı boş bırakılamaz")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Soyisim Alanını Lütfen Kontrol Ediniz")
    @Size(min = 2  , max = 25 , message = "Fazla veya az karakter kullanıldı")
    private  String lastName;

    @NotBlank(message = "Mail Alanı Boş Bırakılamaz")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Lütfen Geçerli Bir Mail Giriniz")
    private String email;
}
