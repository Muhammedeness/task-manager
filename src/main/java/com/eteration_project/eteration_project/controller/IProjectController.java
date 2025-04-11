package com.eteration_project.eteration_project.controller;

import com.eteration_project.eteration_project.dto.AssignUserDto;
import com.eteration_project.eteration_project.dto.ProjectDto;
import com.eteration_project.eteration_project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.dto.UserDeleteDto;

public interface IProjectController {

    ProjectDto createProject(ProjectSaveDto projectSaveDto);

    String assignUserToProject(AssignUserDto assignUserDto);

    String unAssignUserFromProject(AssignUserDto assignUserDto);

}
