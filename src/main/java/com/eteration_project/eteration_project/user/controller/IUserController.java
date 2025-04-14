package com.eteration_project.eteration_project.user.controller;

import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.user.dto.UserResponseDto;
import com.eteration_project.eteration_project.user.dto.UserSaveDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserController {

    UserResponseDto create(UserSaveDto userSaveDto);

    List<UserResponseDto> listAllUsers();

    ResponseEntity<?> remove(UserDeleteDto userDeleteDto);
}
