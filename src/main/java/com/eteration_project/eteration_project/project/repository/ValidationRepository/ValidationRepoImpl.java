package com.eteration_project.eteration_project.project.repository.ValidationRepository;

import com.eteration_project.eteration_project.project.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ValidationRepoImpl {

    private final JdbcTemplate jdbcTemplate;
    private final ProjectMapper projectMapper;

    public boolean findProjectByName(String projectName){

        try {
            String sql = "SELECT EXISTS(SELECT 1 FROM projects WHERE project_name= ?)";
            Boolean exists = jdbcTemplate.queryForObject( sql, Boolean.class , projectName);
            return exists;
        }catch (EmptyResultDataAccessException e){

            return false;
        }
    }

    public boolean isUserAssignedToProject(UUID projectId , UUID userId){
        try {
            String sql = "SELECT EXISTS(SELECT 1 FROM project_user WHERE project_id= ? AND user_id=?)";
            Boolean exists = jdbcTemplate.queryForObject( sql, Boolean.class , projectId , userId);
            return exists;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public boolean isProjectDone(UUID projectId){
        String sql = "SELECT CASE WHEN state = 'DONE' THEN TRUE ELSE FALSE END FROM projects WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{projectId}, Boolean.class);
    }
}
