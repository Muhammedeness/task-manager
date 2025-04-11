package com.eteration_project.eteration_project.controller;

import com.eteration_project.eteration_project.dto.AssignUserDto;
import com.eteration_project.eteration_project.dto.ProjectDto;
import com.eteration_project.eteration_project.dto.ProjectSaveDto;

public interface IProjectController {

    ProjectDto createProject(ProjectSaveDto projectSaveDto);

    String assignUserToProject(AssignUserDto assignUserDto);
}
