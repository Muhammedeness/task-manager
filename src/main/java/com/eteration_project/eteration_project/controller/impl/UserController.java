package com.eteration_project.eteration_project.controller.impl;

import com.eteration_project.eteration_project.controller.IUserController;
import com.eteration_project.eteration_project.dto.UserDto;
import com.eteration_project.eteration_project.dto.UserSaveDto;
import com.eteration_project.eteration_project.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController implements IUserController {


    @Autowired
    private IUserService iUserService;

    @PostMapping(path = "/create" )
    @Override
    public UserDto createUser(@RequestBody @Valid UserSaveDto userSaveDto) {
        return iUserService.createUser(userSaveDto);
    }
}
