package com.eteration_project.eteration_project.controller;

import com.eteration_project.eteration_project.dto.UserDto;
import com.eteration_project.eteration_project.dto.UserSaveDto;

public interface IUserController {

    UserDto createUser(UserSaveDto userSaveDto);

    //UserDto findUserByEmail(String email);
}
