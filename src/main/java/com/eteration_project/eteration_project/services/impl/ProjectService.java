package com.eteration_project.eteration_project.services.impl;

import com.eteration_project.eteration_project.Mapper.MapStruct.ProjectMapper;
import com.eteration_project.eteration_project.dto.AssignUserDto;
import com.eteration_project.eteration_project.dto.ProjectDto;
import com.eteration_project.eteration_project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.exception.CustomDataExistsException;
import com.eteration_project.eteration_project.exception.CustomNotFoundException;
import com.eteration_project.eteration_project.model.Project;
import com.eteration_project.eteration_project.repository.ProjectRepository;
import com.eteration_project.eteration_project.services.IProjectService;
import org.apache.coyote.BadRequestException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Override
    public String assignUserToProject(AssignUserDto assignUserDto) {

        if (projectRepository.isUserAssignedToProject(assignUserDto)) {
            return "Bu Kullanıcı Zaten Projeye Atanmıştır";
        }

        try {
            projectRepository.assignUserToProject(assignUserDto);
            return "Kullanıcı Başarıyla Projeye Atandı";
        }
        catch (EmptyResultDataAccessException e)
        {
            throw  new CustomNotFoundException("Kullanıcı Bulunamadı");
        }
    }

    @Override
    public String unAssignUserFromProject(AssignUserDto assignUserDto) {
        if (!projectRepository.isUserAssignedToProject(assignUserDto)) {
            return "Bu Kullanıcı Projeye Atanmamıştır";
        }
        try {
            projectRepository.unAssignUserFromProject(assignUserDto);
            return "Kullanıcı Başarıyla Projeden Kaldırıldı";
        }
        catch (EmptyResultDataAccessException e)
        {
            throw  new CustomNotFoundException("Kullanıcı Bulunamadı");
        }
    }
}
