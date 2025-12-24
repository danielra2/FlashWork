package mycode.flashwork2.workerProfile.dtos;

import jakarta.validation.constraints.NotBlank;

public record WorkerProfileDto(
        @NotBlank(message = "Prenumele este obligatoriu")
        String firstName,
        @NotBlank(message = "Numele este obligatoriu")
        String lastName,
        String phone,
        String skills
) {}