package mycode.flashwork2.enrollment.dto;

import mycode.flashwork2.enrollment.models.EnrollmentStatus;
import mycode.flashwork2.workerProfile.models.WorkerSkill;

import java.time.LocalDateTime;
import java.util.List;

public record EnrollmentResponse(
        Long id,
        Long jobId,
        String jobTitle,
        String companyName,
        LocalDateTime jobStartTime,
        LocalDateTime jobEndTime,
        Long workerId,
        String workerFirstName,
        String workerLastName,
        List<WorkerSkill> workerSkills,
        LocalDateTime appliedAt,
        EnrollmentStatus status,
        LocalDateTime clockInTime,
        LocalDateTime clockOutTime
) {}