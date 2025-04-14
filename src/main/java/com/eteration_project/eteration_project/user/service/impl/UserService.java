package com.eteration_project.eteration_project.user.service.impl;


import com.eteration_project.eteration_project.common.exception.CustomNotFoundException;
import com.eteration_project.eteration_project.common.exception.CustomRuntimeException;
import com.eteration_project.eteration_project.project.repository.ProjectRepository;
import com.eteration_project.eteration_project.user.dto.AssignUserDto;
import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.user.dto.UserResponseDto;
import com.eteration_project.eteration_project.user.dto.UserSaveDto;
import com.eteration_project.eteration_project.user.mapper.UserMapper;
import com.eteration_project.eteration_project.user.model.User;
import com.eteration_project.eteration_project.user.repository.UserRepository;
import com.eteration_project.eteration_project.user.service.IUserService;
import com.eteration_project.eteration_project.user.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    private final UserMapper userMapper;
    private final UserValidator userValidator;
    private final MessageSource messageSource;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public UserResponseDto create(UserSaveDto userSaveDto) {

        userValidator.isUserExistsCreateValidation(userSaveDto.getEmail());    //kullanıcı kontrolünü validator aracılıgı ile yaptık

        UserResponseDto userDto = new UserResponseDto();
        User savedUser = userRepository.save(userSaveDto);
        userDto = userMapper.userToUserDto(savedUser);//map struct ile entity ile dto map edildi

        return userDto;
    }


    @Override
    public List<UserResponseDto> listAllUsers() {

        List<UserResponseDto> userDtoList = new ArrayList<>();
        List<User> usersList = userRepository.getAllUsers();
        if (usersList.isEmpty()) {
            throw new CustomNotFoundException("error.user.not.found");
        }
        for (User user : usersList) {
            UserResponseDto userDto = userMapper.userToUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public void remove(UserDeleteDto userDeleteDto) {

        AssignUserDto assignUserDto= userMapper.userDeleteDtoToassignUserDto(userDeleteDto);

        userValidator.isUserExistsRemoveValidation(userDeleteDto.getEmail());
        userValidator.isUserAssignedValidation(assignUserDto);
        userRepository.deleteUser(userDeleteDto);
    }
}
