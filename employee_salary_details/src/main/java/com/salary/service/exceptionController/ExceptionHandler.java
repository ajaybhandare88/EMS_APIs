package com.salary.service.exceptionController;

import com.salary.service.exception.EmptyException;
import com.salary.service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> notFoundException(ResourceNotFoundException ex)
    {
        Map<String,String> map=new HashMap<>();
        map.put("Message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyException.class)
    public ResponseEntity<Map<String,String>> emptyException(EmptyException ex)
    {
        Map<String,String> map=new HashMap<>();
        map.put("Message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

}
