package com.eteration_project.eteration_project.project.repository.RepositoryImpl;

import com.eteration_project.eteration_project.common.exception.CustomRuntimeException;
import com.eteration_project.eteration_project.project.mapper.rowMapper.ProjectRowMapper;
import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import com.eteration_project.eteration_project.project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.project.model.Project;
import com.eteration_project.eteration_project.project.repository.ProjectRepository;
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
        String sql = "INSERT INTO projects (project_name, description) VALUES (?, ?) RETURNING id";

        Integer newId = jdbcTemplate.queryForObject(
                sql,
                new Object[]{projectSaveDto.getProjectName(), projectSaveDto.getDescription()},
                Integer.class
        );

        Project project = new Project();
        project.setId(newId);
        project.setProjectName(projectSaveDto.getProjectName());
        project.setDescription(projectSaveDto.getDescription());

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

    @Override
    public void assignUserToProject(AssignUserDto assignUserDto) {
        Integer projectId = this.findProjectIdByName(assignUserDto.getProjectName());
        Integer userId = this.findUserIdByDetails(assignUserDto.getEmail() , assignUserDto.getFirstName() , assignUserDto.getLastName());

        String assignSql = "INSERT INTO project_user (project_id, user_id) VALUES (?, ?)";
        jdbcTemplate.update(assignSql, projectId, userId);
    }

    @Override
    public Boolean isUserAssigned(AssignUserDto assignUserDto) {

        Integer userId = this.findUserIdByDetails(assignUserDto.getEmail() , assignUserDto.getFirstName() , assignUserDto.getLastName());
        String checkSql = "SELECT COUNT(*) FROM project_user WHERE user_id = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, new Object[]{userId}, Integer.class);
        return count > 0;
    }

    @Override
    public Boolean isUserAssignedToProject(AssignUserDto assignUserDto) {

        Integer userId = this.findUserIdByDetails(assignUserDto.getEmail() , assignUserDto.getFirstName() , assignUserDto.getLastName());
        Integer projectId = this.findProjectIdByName(assignUserDto.getProjectName());


        String sql = "SELECT COUNT(*) FROM project_user WHERE user_id = ? AND project_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{userId, projectId}, Integer.class);

        return count > 0;

    }

    @Override
    public void unAssignUserFromProject(AssignUserDto assignUserDto) {

            Integer userId = this.findUserIdByDetails(assignUserDto.getEmail() , assignUserDto.getFirstName() , assignUserDto.getLastName());
            Integer projectId = this.findProjectIdByName(assignUserDto.getProjectName());

            //project_user tablosundan bu eşleşmeyi sil
            String deleteSql = "DELETE FROM project_user WHERE user_id = ? AND project_id = ?";
            int rowsAffected = jdbcTemplate.update(deleteSql, userId, projectId);

            //return rowsAffected > 0;

    }


    @Override
    public Integer findUserIdByDetails(String email , String firstName , String lastName) {
        // Kullanıcının ID'sini al
        String userSql = "SELECT id FROM users WHERE email = ? AND first_name = ? AND last_name = ?";
        try {
            Integer userId = jdbcTemplate.queryForObject(userSql, new Object[]{email,
                    firstName,
                    lastName}, Integer.class);
            return userId;
        }catch (EmptyResultDataAccessException e){
            throw new CustomRuntimeException("error.user.not.found");
        }
    }

    @Override
    public Integer findProjectIdByName(String projectName) {
        String projectSql = "SELECT id FROM projects WHERE project_name = ?";
        try {
            Integer projectId = jdbcTemplate.queryForObject(projectSql, new Object[]{projectName}, Integer.class);
            return  projectId;

        }catch (EmptyResultDataAccessException e){
            throw new CustomRuntimeException("error.project.not.found");
        }
    }
}
