package mycode.flashwork2.users.controller;

import mycode.flashwork2.users.exceptions.EmailAlreadyInUse;
import mycode.flashwork2.users.exceptions.EmailAlreadyInUse;
import mycode.flashwork2.users.exceptions.UserDoesntExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "mycode.flashwork2.users")
public class UserExceptionHandler {

    // 1. Gestionare Email deja utilizat (409 CONFLICT)
    @ExceptionHandler(EmailAlreadyInUse.class)
    public ResponseEntity<Object> handleEmailAlreadyInUse(EmailAlreadyInUse ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.CONFLICT.value()); // 409

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    // 2. Gestionare Utilizator inexistent (404 NOT FOUND)
    @ExceptionHandler(UserDoesntExistException.class)
    public ResponseEntity<Object> handleUserNotFound(UserDoesntExistException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value()); // 404

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // 3. Gestionare erori de validare (400 BAD REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("errors", errors);
        body.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // 4. Gestionare erori generice de business
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}