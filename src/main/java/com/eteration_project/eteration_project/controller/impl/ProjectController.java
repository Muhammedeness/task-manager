package com.eteration_project.eteration_project.controller.impl;

import com.eteration_project.eteration_project.controller.IProjectController;
import com.eteration_project.eteration_project.controller.IUserController;
import com.eteration_project.eteration_project.dto.AssignUserDto;
import com.eteration_project.eteration_project.dto.ProjectDto;
import com.eteration_project.eteration_project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.services.IProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController  implements IProjectController {


    @Autowired
    private IProjectService iProjectService;

    @PostMapping(path = "/create")
    @Override
    public ProjectDto createProject( @RequestBody @Valid ProjectSaveDto projectSaveDto) {
        return iProjectService.createProject(projectSaveDto) ;
    }

    @PostMapping(path = "/assign")
    @Override
    public String assignUserToProject(@RequestBody AssignUserDto assignUserDto) {
        return  iProjectService.assignUserToProject(assignUserDto) ;
    }
}
