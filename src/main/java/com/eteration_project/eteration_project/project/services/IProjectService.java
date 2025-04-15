package com.eteration_project.eteration_project.project.services;

import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;

import java.util.List;

public interface IProjectService {


    ProjectResponseDto create(ProjectSaveDto projectSaveDto);

    String assignUserToProject(AssignUserDto assignUserDto);

    String unAssignUserFromProject(AssignUserDto assignUserDto);

    List<ProjectResponseDto> listAllProjects();

    void delete(String projectName);
}
