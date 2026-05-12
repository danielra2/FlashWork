package mycode.flashwork2.enrollment.dto;

import mycode.flashwork2.enrollment.models.EnrollmentStatus;

import java.time.LocalDateTime;

public record EnrollmentResponse(
        Long id,
        Long jobId,
        String jobTitle,
        Long workerId,
        String workerFirstName,
        String workerLastName,
        LocalDateTime appliedAt,
        EnrollmentStatus status
) {}