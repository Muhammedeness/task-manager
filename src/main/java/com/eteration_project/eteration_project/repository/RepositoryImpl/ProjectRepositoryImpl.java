package com.eteration_project.eteration_project.repository.RepositoryImpl;

import com.eteration_project.eteration_project.Mapper.RowMapper.ProjectRowMapper;
import com.eteration_project.eteration_project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.model.Project;
import com.eteration_project.eteration_project.repository.ProjectRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {


    private final JdbcTemplate jdbcTemplate;
    private ProjectRowMapper projectRowMapper;

    public ProjectRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.projectRowMapper= new ProjectRowMapper();
    }

    @Override
    public Project save(ProjectSaveDto projectSaveDto) {
        String sql = "INSERT INTO projects (project_name, description, status) VALUES (?, ?, ?) RETURNING id";

        Integer newId = jdbcTemplate.queryForObject(
                sql,
                new Object[]{projectSaveDto.getProjectName(), projectSaveDto.getDescription(), projectSaveDto.getStatus()},
                Integer.class
        );

        Project project = new Project();
        project.setId(newId);
        project.setProjectName(projectSaveDto.getProjectName());
        project.setDescription(projectSaveDto.getDescription());
        project.setStatus(projectSaveDto.getStatus());

        return project;
    }

    @Override
    public Optional<Project> getProjectByName(String projectName) {

        try {

            String sql = "SELECT * FROM projects WHERE project_name = ?";
            Project project=  jdbcTemplate.queryForObject(sql , new Object[]{projectName} , new ProjectRowMapper());
            return Optional.ofNullable(project);

        }catch (EmptyResultDataAccessException e){

            return Optional.empty();

        }
    }
}
