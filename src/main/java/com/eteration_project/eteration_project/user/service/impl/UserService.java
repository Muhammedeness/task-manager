package com.eteration_project.eteration_project.user.service.impl;

import com.eteration_project.eteration_project.user.dto.UserResponseDto;
import com.eteration_project.eteration_project.user.mapper.UserMapper;
import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.user.dto.UserSaveDto;
import com.eteration_project.eteration_project.common.exception.CustomNotFoundException;
import com.eteration_project.eteration_project.common.exception.CustomDataExistsException;
import com.eteration_project.eteration_project.user.model.User;
import com.eteration_project.eteration_project.project.repository.ProjectRepository;
import com.eteration_project.eteration_project.user.repository.UserRepository;
import com.eteration_project.eteration_project.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
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

    //userService icinde createUser servisinde kullanılmak üzere kullanıcının db de varlığını kontrol eden servis
    @Override
    public Boolean isUserExistsByEmail(String mail) {

        Optional<User> user = userRepository.findUserByEmail(mail);
        if (user.isPresent()) {
            return true;
        }else
            return false;
    }

    @Override
    public List<UserResponseDto> listAllUsers() {

        List<UserResponseDto> userDtoList = new ArrayList<>();
        List<User> usersList = userRepository.getAllUsers();
        if (usersList.isEmpty()) {
            throw new CustomNotFoundException("Kullanıcı BUlunamadı");
        }
        for (User user : usersList) {
            UserResponseDto userDto = userMapper.userToUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public String remove(UserDeleteDto userDeleteDto) {

        if (projectRepository.isUserAssigned(userDeleteDto))
            return  messageSource.getMessage("error.user.removed" , null , Locale.getDefault());

        if (isUserExistsByEmail(userDeleteDto.getEmail())){

            userRepository.deleteUser(userDeleteDto.getEmail());
            return  messageSource.getMessage("success.user.removed" , null , Locale.getDefault());
        }else
            throw new CustomNotFoundException("Silinmek İstenen Kullanıcı Bulunumadı");
    }
}
