package com.eteration_project.eteration_project.project.validation;

import com.eteration_project.eteration_project.common.exception.CustomDataExistsException;
import com.eteration_project.eteration_project.common.exception.CustomRuntimeException;
import com.eteration_project.eteration_project.project.model.Project;
import com.eteration_project.eteration_project.project.repository.ProjectRepository;
import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectValidator {

    private  final ProjectRepository projectRepository;

    public void isProjectCreated(String projectName){

        Optional<Project> project = projectRepository.getProjectByName(projectName);

        if (project.isPresent()) {
            throw  new CustomDataExistsException("error.project.create");
        }
    }

    public void isUserAssignedToProjectValidation(AssignUserDto assignUserDto){
        if (projectRepository.isUserAssignedToProject(assignUserDto)) {
            throw new CustomRuntimeException("user.assigned");
        }
    }

}
