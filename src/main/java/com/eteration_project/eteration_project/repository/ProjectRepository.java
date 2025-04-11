package com.eteration_project.eteration_project.repository;

import com.eteration_project.eteration_project.dto.AssignUserDto;
import com.eteration_project.eteration_project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.dto.UserDeleteDto;
import com.eteration_project.eteration_project.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    Project save(ProjectSaveDto projectSaveDto);

    Optional<Project> getProjectByName(String projectName);


    void assignUserToProject(AssignUserDto assignUserDto);

    Boolean isUserAssigned(UserDeleteDto userDeleteDto);

    Boolean isUserAssignedToProject(AssignUserDto assignUserDto);

    Boolean unAssignUserFromProject(AssignUserDto assignUserDto);


}
