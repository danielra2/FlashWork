package mycode.flashwork2.enrollment.dto;

import jakarta.validation.constraints.NotNull;

public record CreateEnrollmentRequest(
        @NotNull(message="Job ID is required")
        Long jobId,
        @NotNull(message="Worker ID is required")
        Long workerId
) {}