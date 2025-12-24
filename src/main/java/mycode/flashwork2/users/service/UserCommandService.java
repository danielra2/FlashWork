package mycode.flashwork2.users.service;

import mycode.flashwork2.users.dtos.UserRegistrationRequest;
import mycode.flashwork2.users.dtos.UserResponse;

public interface UserCommandService {
    UserResponse registerUser(UserRegistrationRequest request);
    UserResponse deleteUser(Long userId); // Modificat din void
    UserResponse updatePassword(Long userId, String newPassword);
}
