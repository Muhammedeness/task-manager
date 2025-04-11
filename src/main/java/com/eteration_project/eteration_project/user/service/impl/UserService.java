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
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(UserService.class);



    private final UserMapper userMapper;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDto create(UserSaveDto userSaveDto) {

         Boolean  isUserExists=this.isUserExistsByEmail(userSaveDto.getEmail());
         //if users exists in db will return true or false

        if ( isUserExists ){
            throw  new CustomDataExistsException("Kullanıcı Kayıtlı");
        } else {
            User savedUser = new User();
            UserResponseDto userDto = new UserResponseDto();
            savedUser = userRepository.save(userSaveDto);
           userDto= userMapper.userToUserDto(savedUser);//map struct ile entity ile dto map edildi

            return  userDto;
        }
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
            throw new CustomNotFoundException("Sİlinmek İstenen Kullanıcı Bulunumadı");
    }
}
