package com.eteration_project.eteration_project.services;

import com.eteration_project.eteration_project.dto.AssignUserDto;
import com.eteration_project.eteration_project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.dto.ProjectSaveDto;

public interface IProjectService {


    ProjectResponseDto createProject(ProjectSaveDto projectSaveDto);

    String assignUserToProject(AssignUserDto assignUserDto);

    String unAssignUserFromProject(AssignUserDto assignUserDto);

}
