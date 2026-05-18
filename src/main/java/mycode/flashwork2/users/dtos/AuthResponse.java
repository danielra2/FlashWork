package mycode.flashwork2.users.dtos;

import mycode.flashwork2.users.models.UserType;

public record AuthResponse(
        Long userId,
        String email,
        UserType userType,
        String token
) {}