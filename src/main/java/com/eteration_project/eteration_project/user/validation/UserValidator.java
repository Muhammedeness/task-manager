package com.eteration_project.eteration_project.user.validation;

import com.eteration_project.eteration_project.common.exception.CustomDataExistsException;
import com.eteration_project.eteration_project.common.exception.CustomNotFoundException;
import com.eteration_project.eteration_project.user.model.User;
import com.eteration_project.eteration_project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void isUserExistsCreateValidation(String email) {
        
        if (userRepository.isUserExistsByEmail(email)) {
            throw new CustomDataExistsException("error.user.create");
        }
    }
}
