package com.eteration_project.eteration_project.project.services.impl;

import com.eteration_project.eteration_project.project.mapper.ProjectMapper;
import com.eteration_project.eteration_project.project.validation.ProjectValidator;
import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.project.model.Project;
import com.eteration_project.eteration_project.project.repository.ProjectRepository;
import com.eteration_project.eteration_project.project.services.IProjectService;
import lombok.RequiredArgsConstructor;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;


@Service
@RequiredArgsConstructor
public class ProjectService  implements IProjectService {


    private final ProjectMapper projectMapper;
    private  final MessageSource messageSource;
    private final ProjectRepository projectRepository;
    private  final ProjectValidator projectValidator;


    @Override
    public ProjectResponseDto create(ProjectSaveDto projectSaveDto) {

        projectValidator.isProjectCreated(projectSaveDto.getProjectName());    //if project is alreay created this validator throw error.project.create
        Project savedProject = projectRepository.save(projectSaveDto);
        ProjectResponseDto projectResponseDto = projectMapper.projectToProjectDto(savedProject);
        return projectResponseDto;

    }
    @Override
    public String assignUserToProject(AssignUserDto assignUserDto) {

            projectValidator.isUserAssignedToProjectValidation(assignUserDto);
            projectRepository.assignUserToProject(assignUserDto);
            return  messageSource.getMessage("success.user.assign" , null , Locale.getDefault());

    }

    @Override
    public String unAssignUserFromProject(AssignUserDto assignUserDto) {

        projectValidator.isUserNotAssignedToProjectValidation(assignUserDto);
        projectRepository.unAssignUserFromProject(assignUserDto);
        return  messageSource.getMessage("success.user.removedproject" , null , Locale.getDefault());


    }

    @Override
    public List<ProjectResponseDto> listAllProjects() {
        List<ProjectResponseDto> list = projectRepository.getAll();
        projectValidator.isListEmpty(list);
        return list;
    }
}
