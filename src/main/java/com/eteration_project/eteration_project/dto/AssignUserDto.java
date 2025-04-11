package com.eteration_project.eteration_project.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignUserDto {

    private String  projectName;

    private String  userName;

    private  String lastName;

    private String email;
}
