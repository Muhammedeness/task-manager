package com.eteration_project.eteration_project.project.validation;

import com.eteration_project.eteration_project.common.exception.CustomDataExistsException;
import com.eteration_project.eteration_project.project.repository.ValidationRepository.ValidationRepoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectValidator {


    private  final ValidationRepoImpl validationRepo;

    public boolean isProjectCreatedValidator(String projectName){

        Boolean exists = validationRepo.findProjectByName(projectName);
        if (exists) {
            throw  new CustomDataExistsException("error.project.create");
        }
        return exists;
    }

    public boolean isUserAssignedToProjectValidator(UUID projectId , UUID userId){
        Boolean exists = validationRepo.isUserAssignedToProject(projectId , userId);
        if (exists) {
            throw  new CustomDataExistsException("user.assigned");
        }
        return exists;
    }

    public boolean isUserNotAssignedToProjectValidator(UUID projectId , UUID userId){
        Boolean exists = validationRepo.isUserAssignedToProject(projectId , userId);
        if (!exists) {
            throw  new CustomDataExistsException("error.assigned.user.not.found");
        }
        return exists;
    }

    public boolean isProjectDone(){

    }
}

