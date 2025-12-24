package mycode.flashwork2.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import mycode.flashwork2.users.models.UserType;

public record UserRegistrationRequest(
        @NotBlank(message = "Email-ul este obligatoriu")
        @Email(message = "Formatul email-ului este invalid")
        String email,

        @NotBlank(message = "Parola este obligatorie")
        @Size(min = 6, message = "Parola trebuie să aibă cel puțin 6 caractere")
        String password,

        @NotNull(message = "Tipul de utilizator este obligatoriu")
        UserType userType
) {}