package com.eteration_project.eteration_project.services.impl;

import com.eteration_project.eteration_project.Mapper.MapStruct.ProjectMapper;
import com.eteration_project.eteration_project.dto.ProjectDto;
import com.eteration_project.eteration_project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.exception.CustomDataExistsException;
import com.eteration_project.eteration_project.exception.CustomNotFoundException;
import com.eteration_project.eteration_project.model.Project;
import com.eteration_project.eteration_project.repository.ProjectRepository;
import com.eteration_project.eteration_project.services.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService  implements IProjectService {


    private final ProjectMapper projectMapper;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectService(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectDto createProject(ProjectSaveDto projectSaveDto) {

        Optional<Project> project = projectRepository.getProjectByName(projectSaveDto.getProjectName());
        if (project.isPresent()) {
          throw  new CustomDataExistsException("Bu Proje Oluşturulmuştur. Aynı isimle proje oluşturulamaz");
        }else{

            Project savedProject = projectRepository.save(projectSaveDto);

            ProjectDto projectDto = projectMapper.projectToProjectDto(savedProject);
            return projectDto;

        }
    }
}
