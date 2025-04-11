package com.eteration_project.eteration_project.user.controller.impl;

import com.eteration_project.eteration_project.user.controller.IUserController;
import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.user.dto.UserResponseDto;
import com.eteration_project.eteration_project.user.dto.UserSaveDto;
import com.eteration_project.eteration_project.user.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController implements IUserController {

/*
    @Autowired
    private UserRepositoryImpl userRepository;*/

    @Autowired
    private IUserService iUserService;

    @PostMapping(path = "/create" )
    @Override
    public UserResponseDto createUser(@RequestBody @Valid UserSaveDto userSaveDto) {
        return iUserService.createUser(userSaveDto);
    }

    @GetMapping(path = "/list")
    @Override
    public List<UserResponseDto> listAllUsers() {
        return iUserService.listAllUsers();
    }

    @DeleteMapping("/delete")
    @Override
    public String removeUser( @RequestBody  @Valid UserDeleteDto userDeleteDto) {
        return iUserService.removeUser(userDeleteDto);
    }

}
