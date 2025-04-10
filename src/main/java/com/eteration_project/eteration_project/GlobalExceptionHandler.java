package com.eteration_project.eteration_project;

import com.eteration_project.eteration_project.exception.CustomNotFoundException;
import com.eteration_project.eteration_project.exception.CustomUserExistsException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handleCustomNotFoundException (CustomNotFoundException e){
        return e.getMessage();
    }


    @ExceptionHandler(CustomUserExistsException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleCustomUserExistsException(CustomUserExistsException e){
        return e.getMessage();
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleEmptyResultDataAccessException(EmptyResultDataAccessException e){
        e.printStackTrace();
        return e.getMessage();
    }

}
