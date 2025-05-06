package com.eteration_project.eteration_project.project.services.impl;

import com.eteration_project.eteration_project.common.exception.CustomRuntimeException;
import com.eteration_project.eteration_project.keycloak.service.KeycloakService;
import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.project.mapper.ProjectMapper;
import com.eteration_project.eteration_project.project.model.Project;
import com.eteration_project.eteration_project.project.repository.ProjectRepository.ProjectRepoImpl;
import com.eteration_project.eteration_project.project.services.IProjectService;
import com.eteration_project.eteration_project.project.validation.ProjectValidator;
import lombok.RequiredArgsConstructor;


import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProjectService  implements IProjectService {

    private final ProjectRepoImpl projectRepository;
    private final ProjectMapper projectMapper;
    private final ProjectValidator projectValidator;
    private final MessageSource messageSource;
    private final KeycloakService keycloakService;

    /**
     * create METHODU  projectSaveDto İLE projectName ve description ALIR
     * ÖNCE projectValidator İLE PROJE ADI İLE PROJENİN VARLIĞI KONTROL EDİLİR
     * EĞER PROJE VARSA error.project.create FIRLATILIR
     * AKSİ HALDE ProjectRepositoryImpl DEN create METHODU ÇAĞIRARAK KAYDEDER
     *
     * @param projectSaveDto projectName ve description alınır
     * @author Muhammed Enes Selvi
     * */
    @Override
    public ProjectResponseDto create(ProjectSaveDto projectSaveDto) {

        projectValidator.isProjectCreatedValidator(projectSaveDto.getProjectName());
        Project createdProject =  projectRepository.create(projectSaveDto);
        return projectMapper.entityToDto(createdProject) ;  //Project entity sini ProjectResponseDto ya mao eder
    }

    /**
     * assignUserToProject methodu projectId ve userId ile önce  projectValidator ile kontrol eder
     * kontrolden başarıyla geçerse user ı projeye atama işlemi gerçekleşir
     *
     * @param projectName proje adı ile projectId çekilir
     * @param email email ile userId çekilir
     * @author Muhammed Enes Selvi
     * */
    public  String assignUserToProject(String projectName , String email){
        UUID projectId = projectRepository.findProjectIdByName(projectName);
        String userId = keycloakService.getUserDetails(email).getId();  //kullanıcın emaili ile userId döner
        UUID id = UUID.fromString(userId);
        projectValidator.isUserAssignedToProjectValidator(projectId , id);
        projectRepository.assignUserToProject(projectId , id);
        return  messageSource.getMessage("success.user.assign" , null , Locale.getDefault());
    }

    /**
     * assignUserToProject methodu projectId ve userId ile önce  projectValidator ile kontrol eder
     * kontrolden başarıyla geçerse user ı projeden unassign eder
     *
     * @param projectName proje adı ile projectId çekilir
     * @param email email ile userId çekilir
     * @author Muhammed Enes Selvi
     * */
    @Override
    public String unassignUserFromProject(String projectName, String email) {
        UUID projectId = projectRepository.findProjectIdByName(projectName);
        String userId = keycloakService.getUserDetails(email).getId();  //kullanıcın emaili ile userId döner
        UUID id = UUID.fromString(userId);
        Boolean check = projectValidator.isUserNotAssignedToProjectValidator(projectId , id);

        projectRepository.unassignUserFromProject(projectId , id);
        return messageSource.getMessage("success.user.unassign" , null , Locale.getDefault());
    }

    @Override
    public List<ProjectResponseDto> listAllProjects() {
        List<ProjectResponseDto> projectsList = projectRepository.listProjects();
        return projectRepository.listProjects();
    }

    @Override
    public void doneProject(String projectName) {
        UUID projectId = projectRepository.findProjectIdByName(projectName);
        projectRepository.doneProject(projectId);
    }
}
