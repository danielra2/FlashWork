package mycode.flashwork2.users.mappers;

import mycode.flashwork2.users.dtos.UserRegistrationRequest;
import mycode.flashwork2.users.dtos.UserResponse;
import mycode.flashwork2.users.models.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {

    public User mapRegistrationRequestToUser(UserRegistrationRequest request) {
        Objects.requireNonNull(request, "Request-ul de înregistrare este null");

        return User.builder()
                .email(request.email())
                .password(request.password()) // Parola va fi criptata ulterior în Service
                .userType(request.userType())
                .build();
    }

    public UserResponse mapUserToUserResponse(User user) {
        Objects.requireNonNull(user, "Entitatea User este null");

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getUserType(),
                user.getCreatedAt()
        );
    }
}