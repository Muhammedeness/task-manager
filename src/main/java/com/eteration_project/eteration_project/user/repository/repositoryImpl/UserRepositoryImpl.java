package com.eteration_project.eteration_project.user.repository.repositoryImpl;

import com.eteration_project.eteration_project.common.config.PasswordEncoderConfig;
import com.eteration_project.eteration_project.project.dto.ProjectDetailsDto;
import com.eteration_project.eteration_project.project.repository.ProjectRepository;
import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.user.dto.UserResponseDto;
import com.eteration_project.eteration_project.user.dto.UserSaveDto;
import com.eteration_project.eteration_project.user.mapper.UserMapper;
import com.eteration_project.eteration_project.user.model.User;
import com.eteration_project.eteration_project.user.mapper.rowMapper.UserRowMapper;
import com.eteration_project.eteration_project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final ProjectRepository projectRepository;
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;
    private final UserMapper userMapper;
    private final PasswordEncoderConfig passwordEncoderConfig;


    @Override
    public User save(UserSaveDto userSaveDto) {

        //db ye encode edilmiş password kaydedilir.
        //düz string hali ile şifre kaydedilmez
        String encodedPassword = passwordEncoderConfig.passwordEncoder().encode(userSaveDto.getPassword());

        String sql = "INSERT INTO users (first_name, last_name, birth_date, email , password) VALUES (?, ?, ?, ? , ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            // Sadece 'id' kolonunu döndürmesini istiyoruz
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, userSaveDto.getFirstName());
            ps.setString(2, userSaveDto.getLastName());
            ps.setDate(3, userSaveDto.getBirthDate() != null ? new java.sql.Date(userSaveDto.getBirthDate().getTime()) : null);
            ps.setString(4, userSaveDto.getEmail());
            ps.setString(5, encodedPassword);
            return ps;
        }, keyHolder);

        // KeyHolder'dan sadece ID alınır
        Number key = keyHolder.getKey();
        Integer generatedId = (key != null) ? key.intValue() : null;

        // User nesnesini oluştur
        User user = new User();
        user.setId(generatedId);
        user.setFirstName(userSaveDto.getFirstName());
        user.setLastName(userSaveDto.getLastName());
        user.setBirthDate(userSaveDto.getBirthDate());
        user.setEmail(userSaveDto.getEmail());
        user.setPassword(encodedPassword);

        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserRowMapper());
            return user;
        }catch (EmptyResultDataAccessException e)
        {
            throw  new UsernameNotFoundException("User not found with email :" + email);
        }
    }

    @Override
    public List<User> getAllUsers() {

        String sql = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(sql , new BeanPropertyRowMapper<>(User.class));

        return users;

    }

    @Override
    public Integer deleteUser(UserDeleteDto userDeleteDto) {


        Integer userId = projectRepository.findUserIdByDetails(userDeleteDto.getEmail() , userDeleteDto.getFirstName() , userDeleteDto.getLastName());
        String sql = "Delete from users WHERE id= ?";
        return jdbcTemplate.update(sql , userId);
    }


    //userService icinde createUser servisinde kullanılmak üzere kullanıcının db de varlığını kontrol eden servis
    @Override
    public Boolean isUserExistsByEmail(String mail) {
        //todo : repository sql query'si ile true false dönecek
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql , new Object[]{mail}, Integer.class);

        if (count!=0) {
            return true;
        } else
            return false;
    }



}
