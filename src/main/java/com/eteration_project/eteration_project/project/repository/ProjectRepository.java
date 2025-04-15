package com.eteration_project.eteration_project.project.repository;

import com.eteration_project.eteration_project.project.dto.ProjectDetailsDto;
import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.project.model.Project;

import java.util.List;

public interface ProjectRepository {

    Project save(ProjectSaveDto projectSaveDto);

    Boolean findProjectByName(String projectName);

    List<ProjectResponseDto> getAll();

    void assignUserToProject(AssignUserDto assignUserDto);

    Boolean isUserAssigned(AssignUserDto assignUserDto);

    Boolean isUserAssignedToProject(AssignUserDto assignUserDto);

    void unAssignUserFromProject(AssignUserDto assignUserDto);

    Integer findUserIdByDetails(String mail , String firstname , String lastname);

    Integer findProjectIdByName(String projectName);

    void delete(Integer projectId);

    Boolean isProjecthaveUsers(Integer projectId);

    ProjectDetailsDto getUsersByProjectId(Integer projectId);

}
