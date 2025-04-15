package com.eteration_project.eteration_project.project.controller.impl;

import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.project.services.IProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController{


    @Autowired
    private IProjectService projectService;

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
}
