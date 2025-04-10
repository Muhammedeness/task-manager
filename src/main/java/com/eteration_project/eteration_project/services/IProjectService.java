package com.eteration_project.eteration_project.services;

import com.eteration_project.eteration_project.dto.ProjectDto;
import com.eteration_project.eteration_project.dto.ProjectSaveDto;

public interface IProjectService {


    ProjectDto createProject(ProjectSaveDto projectSaveDto);

}
