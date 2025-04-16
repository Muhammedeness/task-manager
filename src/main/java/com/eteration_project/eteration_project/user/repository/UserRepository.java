package com.eteration_project.eteration_project.user.repository;

import com.eteration_project.eteration_project.project.dto.ProjectDetailsDto;
import com.eteration_project.eteration_project.project.dto.ProjectResponseDto;
import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.user.dto.UserResponseDto;
import com.eteration_project.eteration_project.user.dto.UserSaveDto;
import com.eteration_project.eteration_project.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(UserSaveDto userSaveDto);
    User findUserByEmail(String email);
    //Optional<User> findUserByEmail(String email);


    List<User> getAllUsers();

    Integer deleteUser(UserDeleteDto userDeleteDto);

    Boolean isUserExistsByEmail(String email);




}
