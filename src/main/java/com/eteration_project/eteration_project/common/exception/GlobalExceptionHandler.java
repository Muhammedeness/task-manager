package com.eteration_project.eteration_project.common.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


    @ExceptionHandler(CustomDataExistsException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleCustomUserExistsException(CustomDataExistsException e){
        return e.getMessage();
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String handleEmptyResultDataAccessException(EmptyResultDataAccessException e){
        e.printStackTrace();
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationResponseMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        ValidationResponseMessage customMethodArgumentNotValidExceptionMessage = new ValidationResponseMessage();

        for (ObjectError objectError : e.getBindingResult().getAllErrors()) {

            String fieldname  = ((FieldError) objectError).getField();
            customMethodArgumentNotValidExceptionMessage.addErrorToTheMap(fieldname, objectError.getDefaultMessage());
        }
        return customMethodArgumentNotValidExceptionMessage;
    }

}
