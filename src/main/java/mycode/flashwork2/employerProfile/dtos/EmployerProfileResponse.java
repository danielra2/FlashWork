package mycode.flashwork2.employerProfile.dtos;

public record EmployerProfileResponse(
        Long id,
        Long userId,
        String companyName,
        String cui,
        String description
) {}