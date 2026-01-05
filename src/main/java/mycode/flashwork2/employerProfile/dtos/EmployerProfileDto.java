package mycode.flashwork2.employerProfile.dtos;

import jakarta.validation.constraints.NotBlank;

public record EmployerProfileDto(
        @NotBlank(message = "Numele companiei este obligatoriu")
        String companyName,
        String cui,
        String description
) {}