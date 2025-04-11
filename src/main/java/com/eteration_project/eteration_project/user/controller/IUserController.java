package com.eteration_project.eteration_project.user.controller;

import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.user.dto.UserResponseDto;
import com.eteration_project.eteration_project.user.dto.UserSaveDto;

import java.util.List;

public interface IUserController {

    UserResponseDto create(UserSaveDto userSaveDto);

    //UserDto findUserByEmail(String email);

    List<UserResponseDto> listAllUsers();

    String remove(UserDeleteDto userDeleteDto);
}
