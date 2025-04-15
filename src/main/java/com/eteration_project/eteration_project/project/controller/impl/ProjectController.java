package com.eteration_project.eteration_project.project.controller.impl;

import com.eteration_project.eteration_project.project.dto.ProjectDetailsDto;
import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.project.services.IProjectService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController{


    private final MessageSource messageSource;
    private final IProjectService projectService;

    @PostMapping(path = "/create")
    public ProjectResponseDto create(@RequestBody @Valid ProjectSaveDto projectSaveDto) {
        return projectService.create(projectSaveDto) ;
    }

    @PostMapping(path = "/assign")
    public String assignUserToProject(@RequestBody AssignUserDto assignUserDto) {
        return  projectService.assignUserToProject(assignUserDto) ;
    }

    @DeleteMapping("/unassign")
    public String unAssignUserFromProject(@RequestBody AssignUserDto assignUserDto) {
        return projectService.unAssignUserFromProject(assignUserDto);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<ProjectResponseDto>> listAllProjects(){
        return ResponseEntity.ok(projectService.listAllProjects());
    }

    @DeleteMapping(path = "/delete/{projectName}")
    public ResponseEntity<String> deleteProject(@PathVariable String projectName){

        projectService.delete(projectName);
        String responseMsg = messageSource.getMessage("success.project.delete" , null , Locale.getDefault());
        return ResponseEntity.ok(responseMsg);
    }

    @GetMapping(path = "/project-info/{projectName}")
    public ResponseEntity<ProjectDetailsDto> getProjectUserInfos(@PathVariable String projectName){
        return  ResponseEntity.ok(projectService.getProjectUserInfos(projectName));
    }


}
