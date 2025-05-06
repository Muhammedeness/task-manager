package com.eteration_project.eteration_project.project.mapper.rowMapper;

import com.eteration_project.eteration_project.project.model.Project;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Configuration
public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        Project project = new Project();

        project.setId(UUID.fromString(rs.getString("id")));
        project.setProjectName(rs.getString("project_name"));
        project.setDescription(rs.getString("description"));
        project.setState(rs.getString("state"));

        return project;
    }

}
