package com.eteration_project.eteration_project.project.repository.ProjectRepository;

import com.eteration_project.eteration_project.common.exception.CustomRuntimeException;
import com.eteration_project.eteration_project.keycloak.service.KeycloakService;
import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.project.mapper.rowMapper.ProjectRowMapper;
import com.eteration_project.eteration_project.project.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
@RequiredArgsConstructor
public class ProjectRepoImpl {

    private final JdbcTemplate jdbcTemplate;
    private final ProjectRowMapper projectRowMapper;
    private final KeycloakService keycloakService;

    public Project create(ProjectSaveDto projectSaveDto) {
        String sql = "INSERT INTO projects (project_name, description) VALUES (?, ?) RETURNING *";

        Project project = jdbcTemplate.queryForObject(
                sql,
                new Object[]{projectSaveDto.getProjectName(), projectSaveDto.getDescription()}, new ProjectRowMapper()
        );
        return project;
    }
    public UUID findProjectIdByName(String projectName) {
        String Sql = "SELECT id FROM projects WHERE project_name = ?";
        try {
            UUID projectId = jdbcTemplate.queryForObject(Sql, new Object[]{projectName}, UUID.class);
            return  projectId;

        }catch (EmptyResultDataAccessException e){
            throw new CustomRuntimeException("error.project.not.found");
        }
    }
    public void assignUserToProject(UUID projectId , UUID id){
        String sql = "INSERT INTO project_user (project_id, user_id) VALUES (?, ?)";
        jdbcTemplate.update(sql , projectId , id);
    }

    public void unassignUserFromProject(UUID projectId , UUID id){
        String sql = "DELETE FROM project_user  WHERE project_id = ? AND user_id = ?";
        jdbcTemplate.update(sql , projectId , id);
    }

    public List<ProjectResponseDto> listProjects(){
        String sql = "SELECT * FROM projects";
        return jdbcTemplate.query(sql , new BeanPropertyRowMapper<>(ProjectResponseDto.class));
    }

    public void doneProject(UUID projectId){
        String sql = "UPDATE projects\n" +
                "SET state = 'DONE'\n" +
                "WHERE id = ? AND state = 'ACTIVE';\n";
        jdbcTemplate.update(sql , projectId);

    }

}
