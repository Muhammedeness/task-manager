package com.eteration_project.eteration_project.project.repository;

import com.eteration_project.eteration_project.project.dto.ProjectDetailsDto;
import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.project.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    Project save(ProjectSaveDto projectSaveDto);

    Boolean isProjectByName(String projectName);

    List<ProjectResponseDto> getAll();

    void assignUserToProject(AssignUserDto assignUserDto);

    Boolean isUserAssigned(AssignUserDto assignUserDto);

    Boolean isUserAssignedToProject(AssignUserDto assignUserDto);

    void unAssignUserFromProject(AssignUserDto assignUserDto);

    //List<ProjectDetailsDto> getProjectDetails(String projectName);

    Integer findUserIdByDetails(String mail , String firstname , String lastname);

    Integer findProjectIdByName(String projectName);

    void delete(Integer projectId);

    Boolean isProjecthaveUsers(Integer projectId);



}
