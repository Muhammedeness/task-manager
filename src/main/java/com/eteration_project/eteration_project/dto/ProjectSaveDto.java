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
public class ProjectSaveDto {

    @NotBlank(message = "ProjectName alanı boş bırakılamaz")
    @Size(min = 2  , max = 25 , message = "Fazla veya az karakter kullanıldı")
    private  String projectName;

    //@NotBlank(message = "İsim alanı boş bırakılamaz")
    @Size(min = 0  , max = 150 , message = "Fazla veya az karakter kullanıldı")
    private  String  description;

}
