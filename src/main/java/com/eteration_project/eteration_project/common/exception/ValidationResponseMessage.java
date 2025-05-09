package com.eteration_project.eteration_project.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponseMessage {
    private Map<String, String> methodNotValidErrors = new HashMap<>();

    public void addErrorToTheMap(String field , String errorMessage) {

        methodNotValidErrors.put(field, errorMessage);
    }
}
