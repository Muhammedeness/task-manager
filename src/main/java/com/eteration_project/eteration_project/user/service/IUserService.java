package com.eteration_project.eteration_project.user.service;

import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.user.dto.UserResponseDto;
import com.eteration_project.eteration_project.user.dto.UserSaveDto;

import java.util.List;

public interface IUserService {

    UserResponseDto create(UserSaveDto userSaveDto);

    Boolean isUserExistsByEmail(String email);

    List<UserResponseDto> listAllUsers();


    String remove(UserDeleteDto userDeleteDto);

}
