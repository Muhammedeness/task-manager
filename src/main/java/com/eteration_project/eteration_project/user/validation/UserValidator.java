package com.eteration_project.eteration_project.user.validation;

import com.eteration_project.eteration_project.common.exception.CustomDataExistsException;
import com.eteration_project.eteration_project.common.exception.CustomNotFoundException;
import com.eteration_project.eteration_project.common.exception.CustomRuntimeException;
import com.eteration_project.eteration_project.project.repository.ProjectRepository;
import com.eteration_project.eteration_project.user.dto.UserDeleteDto;
import com.eteration_project.eteration_project.user.model.User;
import com.eteration_project.eteration_project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public void isUserExistsCreateValidation(String email) {
        
        if (userRepository.isUserExistsByEmail(email)) {
            throw new CustomDataExistsException("error.user.create");
        }
    }

    public void isUserExistsRemoveValidation(String email) {

        if (!userRepository.isUserExistsByEmail(email)) {
            throw new CustomNotFoundException("error.user.not.found");
        }
    }

    public void isUserAssignedValidation(UserDeleteDto userDeleteDto){

        if (projectRepository.isUserAssigned(userDeleteDto)) {
            throw new CustomRuntimeException("error.user.removed");
        }
    }
}
