package com.eteration_project.eteration_project.project.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "project.name.is.required")
    @Size(min = 2  , max =  100, message = "check.project.name.field")
    private  String projectName;

    @Size(min = 0  , max = 150 , message = "invalid.size")
    private  String  description;

}
