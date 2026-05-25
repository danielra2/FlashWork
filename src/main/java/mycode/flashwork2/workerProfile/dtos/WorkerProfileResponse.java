package mycode.flashwork2.workerProfile.dtos;

import mycode.flashwork2.workerProfile.models.WorkerSkill;

import java.util.List;

public record WorkerProfileResponse(
        Long id,
        Long userId,
        String firstName,
        String lastName,
        String phone,
        List<WorkerSkill> skills,
        Double rating
) {}