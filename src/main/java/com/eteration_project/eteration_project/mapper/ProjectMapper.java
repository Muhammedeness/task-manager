package com.eteration_project.eteration_project.mapper;

import com.eteration_project.eteration_project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponseDto projectToProjectDto(Project project);

}
