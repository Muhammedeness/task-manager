package com.eteration_project.eteration_project.project.services;


import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;


import java.util.List;

public interface IProjectService {

    ProjectResponseDto create( ProjectSaveDto projectSaveDto);
    String assignUserToProject(String projectName , String email);
    String unassignUserFromProject(String projectName ,String email);
    List<ProjectResponseDto> listAllProjects();
    void doneProject(String projectName);
}
