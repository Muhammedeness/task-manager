package com.eteration_project.eteration_project.exception;

public class CustomUserExistsException extends  RuntimeException{

    public CustomUserExistsException(String msg) {
        super(msg);
    }
}
