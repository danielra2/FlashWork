package mycode.flashwork2.workerProfile.controller;

import mycode.flashwork2.workerProfile.exceptions.InvalidRatingException;
import mycode.flashwork2.workerProfile.exceptions.WorkerProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "mycode.flashwork2.workerProfile")
public class WorkerProfileExceptionHandler {

    @ExceptionHandler(WorkerProfileNotFoundException.class)
    public ResponseEntity<Object> handleWorkerProfileNotFound(WorkerProfileNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRatingException.class)
    public ResponseEntity<Object> handleInvalidRating(InvalidRatingException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}