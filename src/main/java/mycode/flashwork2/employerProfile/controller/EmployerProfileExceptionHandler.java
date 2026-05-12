package mycode.flashwork2.employerProfile.controller;

import mycode.flashwork2.employerProfile.exceptions.EmployerProfileNotFoundException;
import mycode.flashwork2.users.exceptions.UserDoesntExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "mycode.flashwork2.employerProfile")
public class EmployerProfileExceptionHandler {

    @ExceptionHandler(EmployerProfileNotFoundException.class)
    public ResponseEntity<Object> handleProfileNotFound(EmployerProfileNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDoesntExistException.class)
    public ResponseEntity<Object> handleUserNotFound(UserDoesntExistException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}