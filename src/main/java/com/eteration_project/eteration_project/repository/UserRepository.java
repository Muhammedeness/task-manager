package com.eteration_project.eteration_project.repository;

import com.eteration_project.eteration_project.dto.UserDto;
import com.eteration_project.eteration_project.dto.UserSaveDto;
import com.eteration_project.eteration_project.model.User;

public interface UserRepository {

    User save(UserSaveDto userSaveDto);
    User findUserByEmail(String email);
}
