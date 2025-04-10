package com.eteration_project.eteration_project.services;

import com.eteration_project.eteration_project.dto.UserDto;
import com.eteration_project.eteration_project.dto.UserSaveDto;
import com.eteration_project.eteration_project.model.User;

public interface IUserService {

    UserDto createUser(UserSaveDto userSaveDto);

    Boolean isUserExistsByEmail(String email);

}
