package com.eteration_project.eteration_project.project.services.impl;

import com.eteration_project.eteration_project.project.mapper.ProjectMapper;
import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.common.exception.CustomDataExistsException;
import com.eteration_project.eteration_project.common.exception.CustomNotFoundException;
import com.eteration_project.eteration_project.project.model.Project;
import com.eteration_project.eteration_project.project.repository.ProjectRepository;
import com.eteration_project.eteration_project.project.services.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
public class ProjectService  implements IProjectService {


    private final ProjectMapper projectMapper;


    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectService(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectResponseDto create(ProjectSaveDto projectSaveDto) {

        Optional<Project> project = projectRepository.getProjectByName(projectSaveDto.getProjectName());
        if (project.isPresent()) {
          throw  new CustomDataExistsException("Bu Proje Oluşturulmuştur. Aynı isimle proje oluşturulamaz");
        }else{

            Project savedProject = projectRepository.save(projectSaveDto);

            ProjectResponseDto projectResponseDto = projectMapper.projectToProjectDto(savedProject);
            return projectResponseDto;
        }
    }
    @Override
    public String assignUserToProject(AssignUserDto assignUserDto) {

        if (projectRepository.isUserAssignedToProject(assignUserDto)) {
            return  messageSource.getMessage("user.assigned" , null , Locale.getDefault());
        }

        try {
            projectRepository.assignUserToProject(assignUserDto);
            return  messageSource.getMessage("success.user.assign" , null , Locale.getDefault());
        }
        catch (EmptyResultDataAccessException e)
        {
            throw  new CustomNotFoundException("Kullanıcı Bulunamadı");
        }
    }

    @Override
    public String unAssignUserFromProject(AssignUserDto assignUserDto) {
        if (!projectRepository.isUserAssignedToProject(assignUserDto)) {
            return  messageSource.getMessage("error.user.unassign" , null , Locale.getDefault());
        }
        try {
            projectRepository.unAssignUserFromProject(assignUserDto);

            return  messageSource.getMessage("success.user.removedproject" , null , Locale.getDefault());
        }
        catch (EmptyResultDataAccessException e)
        {
            throw  new CustomNotFoundException("Kullanıcı Bulunamadı");
        }
    }
}
