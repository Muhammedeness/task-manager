package com.eteration_project.eteration_project.services;

import com.eteration_project.eteration_project.dto.UserDeleteDto;
import com.eteration_project.eteration_project.dto.UserResponseDto;
import com.eteration_project.eteration_project.dto.UserSaveDto;

import java.util.List;

public interface IUserService {

    UserResponseDto createUser(UserSaveDto userSaveDto);

    Boolean isUserExistsByEmail(String email);

    List<UserResponseDto> listAllUsers();


    String removeUser(UserDeleteDto userDeleteDto);

}
