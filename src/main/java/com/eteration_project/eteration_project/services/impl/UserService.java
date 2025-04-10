package com.eteration_project.eteration_project.services.impl;

import com.eteration_project.eteration_project.Mapper.MapStruct.UserMapper;
import com.eteration_project.eteration_project.dto.UserDto;
import com.eteration_project.eteration_project.dto.UserSaveDto;
import com.eteration_project.eteration_project.exception.CustomNotFoundException;
import com.eteration_project.eteration_project.exception.CustomUserExistsException;
import com.eteration_project.eteration_project.model.User;
import com.eteration_project.eteration_project.repository.UserRepository;
import com.eteration_project.eteration_project.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(UserService.class);



    private final UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;


    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserSaveDto userSaveDto) {

         Boolean  isUserExists=this.isUserExistsByEmail(userSaveDto.getEmail());
         //if users exists in db will return true or false

       // LOGGER.info(isUserExists.toString());

        if ( isUserExists ){
            throw  new CustomUserExistsException("Kullanıcı Kayıtlı");
        } else {
            User savedUser = new User();
            UserDto userDto = new UserDto();
            savedUser = userRepository.save(userSaveDto);
           userDto= userMapper.userToUserDto(savedUser);//map struct ile entity ile dto map edildi

            return  userDto;
        }
    }

    @Override
    public Boolean isUserExistsByEmail(String mail) {

        Optional<User> user = userRepository.findUserByEmail(mail);
        if (user.isPresent()) {
            return true;
        }else
            return false;
    }

    @Override
    public List<UserDto> listAllUsers() {

        List<UserDto> userDtoList = new ArrayList<>();
        List<User> usersList = userRepository.getAllUsers();
        if (usersList == null) {
            throw new CustomNotFoundException("Kullanıcı BUlunamadı");
        }
        for (User user : usersList) {
            UserDto userDto = userMapper.userToUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
