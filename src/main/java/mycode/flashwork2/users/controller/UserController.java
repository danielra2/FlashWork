package mycode.flashwork2.users.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mycode.flashwork2.users.dtos.UserLoginRequest;
import mycode.flashwork2.users.dtos.UserRegistrationRequest;
import mycode.flashwork2.users.dtos.UserResponse;
import mycode.flashwork2.users.service.UserCommandService;
import mycode.flashwork2.users.service.UserQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    // Înregistrare utilizator nou
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody UserRegistrationRequest request) {
        return userCommandService.registerUser(request);
    }

    // Actualizare parolă
    @PutMapping("/updatepassword/{userId}")
    public UserResponse updatePassword(@PathVariable Long userId, @RequestBody String newPassword) {
        return userCommandService.updatePassword(userId, newPassword);
    }

    // Ștergere utilizator
    @DeleteMapping("/delete/{userId}")
    public UserResponse deleteUser(@PathVariable Long userId) {
        return userCommandService.deleteUser(userId);
    }
    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody UserLoginRequest request) {
        return userQueryService.login(request);
    }
}