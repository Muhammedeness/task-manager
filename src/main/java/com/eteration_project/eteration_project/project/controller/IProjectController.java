package com.eteration_project.eteration_project.project.controller;

import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;

public interface IProjectController {

    ProjectResponseDto create(ProjectSaveDto projectSaveDto);

    String assignUserToProject(AssignUserDto assignUserDto);

    String unAssignUserFromProject(AssignUserDto assignUserDto);

}
