package com.eteration_project.eteration_project.project.controller.impl;

import com.eteration_project.eteration_project.keycloak.service.KeycloakService;
import com.eteration_project.eteration_project.project.dto.ProjectDetailsDto;
import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.project.services.IProjectService;
import com.eteration_project.eteration_project.user.dto.UserDetailsDTO;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController{


    private final KeycloakService keycloakService;
    private final MessageSource messageSource;
    private final IProjectService projectService;

    @PostMapping("/test")
    public String testMethod(){
        return "Public /test/ | Function has been executed successfully";
    }

    @PostMapping(path = "/create")
    public ProjectResponseDto create(@RequestBody @Valid ProjectSaveDto projectSaveDto) {
        return projectService.create(projectSaveDto) ;
    }

    @PostMapping(path = "/assign")
    public String assignUserToProject(@RequestParam  String projectName , @RequestParam String email) {
        return  projectService.assignUserToProject(projectName , email) ;
    }

    @PostMapping(path = "/unassign")
    public String unassignUserFromProject(@RequestParam  String projectName , @RequestParam String email) {
        return  projectService.unassignUserFromProject(projectName , email) ;
    }

    @PostMapping(path = "/get-admin-access")
    public String getAdminAccess() {
        return  keycloakService.getAdminAccessToken();
    }

    @PostMapping(path = "/list")
    public ResponseEntity<List<ProjectResponseDto>> listProjects() {
        return ResponseEntity.ok(projectService.listAllProjects());
    }

    @PostMapping(path = "/done-project")
    public  ResponseEntity<String> doneProject(@RequestParam String projectName){
        projectService.doneProject(projectName);
        return  ResponseEntity.ok("success.project.done");
    }
}
