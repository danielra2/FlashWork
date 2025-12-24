package mycode.flashwork2.users.dtos;

import mycode.flashwork2.users.models.UserType;
import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        UserType userType,
        LocalDateTime createdAt
) {}