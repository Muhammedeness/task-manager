package com.eteration_project.eteration_project.services.impl;

import com.eteration_project.eteration_project.dto.UserDto;
import com.eteration_project.eteration_project.dto.UserSaveDto;
import com.eteration_project.eteration_project.model.User;
import com.eteration_project.eteration_project.repository.UserRepository;
import com.eteration_project.eteration_project.services.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDto createUser(UserSaveDto userSaveDto) {

        User savedUser = new User();

        UserDto userDto = new UserDto();
        savedUser = userRepository.save(userSaveDto);

        BeanUtils.copyProperties(savedUser , userDto);

        return  userDto;
        // userrepodaki.save(saveDto)
        // return edilen useri alıp usrDto ya dönüştürüp controller a yollayacağım
    }
}
