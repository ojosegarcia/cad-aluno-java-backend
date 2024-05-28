package com.fatec.student.resources.exceptions;

import java.time.Instant;

import javax.swing.text.html.parser.Entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public  ResponseEntity<StandarError> entitNotFoundException(
        EntityNotFoundException e, HttpServletRequest request
        ){
         StandarError error = new StandarError();
        error.setStatus(HttpStatus.NOT_FOUND.value());    
        error.setError("Resource Not Found");
        error.setMessage(e.getMessage());
        error.setTimeStamp(Instant.now());
        error.setPath(request.getRequestURI()); 

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); 
    }
}
