package com.eteration_project.eteration_project.controller;

import com.eteration_project.eteration_project.dto.AssignUserDto;
import com.eteration_project.eteration_project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.dto.ProjectSaveDto;

public interface IProjectController {

    ProjectResponseDto createProject(ProjectSaveDto projectSaveDto);

    String assignUserToProject(AssignUserDto assignUserDto);

    String unAssignUserFromProject(AssignUserDto assignUserDto);

}
