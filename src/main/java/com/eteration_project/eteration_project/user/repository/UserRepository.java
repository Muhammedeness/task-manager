package com.eteration_project.eteration_project.user.repository;

import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.user.dto.UserSaveDto;
import com.eteration_project.eteration_project.user.model.User;

import java.util.List;


public interface UserRepository {

    User save(UserSaveDto userSaveDto);
    User findUserByEmail(String email);

    List<User> getAllUsers();

    Integer deleteUser(UserDeleteDto userDeleteDto);

    Boolean isUserExistsByEmail(String email);




}
