package com.eteration_project.eteration_project.repository.RepositoryImpl;

import com.eteration_project.eteration_project.Mapper.RowMapper.ProjectRowMapper;
import com.eteration_project.eteration_project.dto.AssignUserDto;
import com.eteration_project.eteration_project.dto.ProjectSaveDto;
import com.eteration_project.eteration_project.dto.UserDeleteDto;
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

    @Override
    public void assignUserToProject(AssignUserDto assignUserDto) {
        String findUserSql = "SELECT id FROM users WHERE email = ?";
        Integer userId = jdbcTemplate.queryForObject(findUserSql, new Object[]{assignUserDto.getEmail()}, Integer.class);

        String findProjectSql = "SELECT id FROM projects WHERE project_name = ?";
        Integer projectId = jdbcTemplate.queryForObject(findProjectSql, new Object[]{assignUserDto.getProjectName()}, Integer.class);

        String assignSql = "INSERT INTO project_user (project_id, user_id) VALUES (?, ?)";
        jdbcTemplate.update(assignSql, projectId, userId);
    }

    @Override
    public Boolean isUserAssigned(UserDeleteDto userDeleteDto) {
        try {
            // Kullanıcının ID'sini al
            String userSql = "SELECT id FROM users WHERE email = ?";
            Integer userId = jdbcTemplate.queryForObject(userSql, new Object[]{userDeleteDto.getEmail()}, Integer.class);

            // Kullanıcının herhangi bir projeye atanıp atanmadığını kontrol et
            String checkSql = "SELECT COUNT(*) FROM project_user WHERE user_id = ?";
            Integer count = jdbcTemplate.queryForObject(checkSql, new Object[]{userId}, Integer.class);

            return count != null && count > 0;
        } catch (EmptyResultDataAccessException e) {
            // Kullanıcı bulunamazsa, zaten hiçbir projeye atanmış olamaz
            return false;
        }
    }

    @Override
    public Boolean isUserAssignedToProject(AssignUserDto assignUserDto) {
        try {
            // Kullanıcının ID'sini al
            String userSql = "SELECT id FROM users WHERE email = ?";
            Integer userId = jdbcTemplate.queryForObject(userSql, new Object[]{assignUserDto.getEmail()}, Integer.class);

            // Kullanıcının herhangi bir projeye atanıp atanmadığını kontrol et
            String projectSql = "SELECT id FROM projects WHERE project_name = ?";
            Integer projectId = jdbcTemplate.queryForObject(projectSql, new Object[]{assignUserDto.getProjectName()}, Integer.class);

            String checkSql = "SELECT COUNT(*) FROM project_user WHERE user_id = ? AND project_id = ?";
            Integer count = jdbcTemplate.queryForObject(checkSql, new Object[]{userId, projectId}, Integer.class);


            return count != null && count > 0;
        } catch (EmptyResultDataAccessException e) {
            // Kullanıcı bulunamazsa, zaten hiçbir projeye atanmış olamaz
            return false;
        }
    }

}
