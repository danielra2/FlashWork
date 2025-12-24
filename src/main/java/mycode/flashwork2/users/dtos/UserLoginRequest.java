package mycode.flashwork2.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
        @NotBlank(message = "Email-ul este obligatoriu")
        @Email(message = "Formatul email-ului este invalid")
        String email,

        @NotBlank(message = "Parola este obligatorie")
        String password
) {}