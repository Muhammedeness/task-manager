package com.eteration_project.eteration_project.user.controller.impl;

import com.eteration_project.eteration_project.user.controller.IUserController;
import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.user.dto.UserResponseDto;
import com.eteration_project.eteration_project.user.dto.UserSaveDto;
import com.eteration_project.eteration_project.user.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController implements IUserController {

    private final IUserService userService;
    private final MessageSource messageSource;

    @PostMapping(path = "/create")
    @Override
    public UserResponseDto create(@RequestBody @Valid UserSaveDto userSaveDto) {
        return userService.create(userSaveDto);
    }

    @GetMapping(path = "/list")
    @PreAuthorize("hasRole('USER')")
    @Override
    public List<UserResponseDto> listAllUsers() {
        return userService.listAllUsers();
    }

    @DeleteMapping(path = "/delete")
    @Override
    public ResponseEntity<String> remove(@RequestBody @Valid UserDeleteDto userDeleteDto) {
        userService.remove(userDeleteDto);

        String responseMsg = messageSource.getMessage("success.user.removed" , null , Locale.getDefault());
        return ResponseEntity.ok(responseMsg);
    }

}
