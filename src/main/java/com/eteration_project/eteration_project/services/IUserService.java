package com.eteration_project.eteration_project.services;

import com.eteration_project.eteration_project.dto.UserDeleteDto;
import com.eteration_project.eteration_project.dto.UserDto;
import com.eteration_project.eteration_project.dto.UserSaveDto;
import com.eteration_project.eteration_project.model.User;

import java.util.List;

public interface IUserService {

    UserDto createUser(UserSaveDto userSaveDto);

    Boolean isUserExistsByEmail(String email);

    List<UserDto> listAllUsers();


    String removeUser(UserDeleteDto userDeleteDto);

}
