package mycode.flashwork2.jobs.dtos;

import mycode.flashwork2.jobs.models.JobStatus;
import java.time.LocalDateTime;

public record JobResponse(
        Long id,
        String title,
        String description,
        Double hourlyRate,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String location,
        JobStatus status,
        Long employerId,
        String companyName
) {}