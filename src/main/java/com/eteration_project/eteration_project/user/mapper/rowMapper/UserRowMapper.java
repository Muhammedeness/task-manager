package com.eteration_project.eteration_project.user.mapper.rowMapper;

import com.eteration_project.eteration_project.user.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id")); // Dikkat: Modelde Integer, ResultSet'tan getInt alınmalı
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setBirthDate(rs.getDate("birth_date")); // ResultSet'tan Date alınmalı
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        // projects listesini burada doldurmak için ek sorgular gerekebilir,
        // bu RowMapper sadece User nesnesini temel sütunlardan oluşturur.
        return user;
    }

}
