package mycode.flashwork2.workerProfile.dtos;

public record WorkerProfileResponse(
        Long id,
        Long userId,
        String firstName,
        String lastName,
        String phone,
        String skills,
        Double rating
) {}