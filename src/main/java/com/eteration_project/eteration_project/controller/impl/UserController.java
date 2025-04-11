package com.eteration_project.eteration_project.controller.impl;

import com.eteration_project.eteration_project.controller.IUserController;
import com.eteration_project.eteration_project.dto.UserDeleteDto;
import com.eteration_project.eteration_project.dto.UserResponseDto;
import com.eteration_project.eteration_project.dto.UserSaveDto;
import com.eteration_project.eteration_project.services.IUserService;
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
