package com.eteration_project.eteration_project.controller;

import com.eteration_project.eteration_project.dto.UserDeleteDto;
import com.eteration_project.eteration_project.dto.UserDto;
import com.eteration_project.eteration_project.dto.UserSaveDto;

import java.util.List;

public interface IUserController {

    UserDto createUser(UserSaveDto userSaveDto);

    //UserDto findUserByEmail(String email);

    List<UserDto> listAllUsers();

    String removeUser(UserDeleteDto userDeleteDto);
}
