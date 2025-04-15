package com.eteration_project.eteration_project.project.dto;

import com.eteration_project.eteration_project.user.dto.UserResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDetailsDto {
/*
    private String projectName;

    private String description;

    private String firstName;

    private String lastName;

    private String email;
*/
    private String projectName;

    private String description;

    private List<UserResponseDto> assignedUsers;


}
