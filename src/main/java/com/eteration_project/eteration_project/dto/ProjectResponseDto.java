package com.eteration_project.eteration_project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {

    @NotBlank(message = "Boş olamaz")
    private  String projectName;

    @NotBlank(message = "Boş olamaz")
    private  String  description;

}


