package mycode.flashwork2.jobs.controller;

import mycode.flashwork2.jobs.exceptions.JobAlreadyExistsException;
import mycode.flashwork2.jobs.exceptions.JobDoesntExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "mycode.flashwork2.jobs")
public class JobExceptionHandler {

    @ExceptionHandler(JobDoesntExistException.class)
    public ResponseEntity<Object> handleJobDoesNotExist(JobDoesntExistException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JobAlreadyExistsException.class)
    public ResponseEntity<Object> handleJobAlreadyExists(JobAlreadyExistsException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}