package mycode.flashwork2.jobs.mappers;

import mycode.flashwork2.jobs.dtos.JobDto;
import mycode.flashwork2.jobs.dtos.JobListResponse;
import mycode.flashwork2.jobs.dtos.JobResponse;
import mycode.flashwork2.jobs.models.Job;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JobMapper {


    private static String nvl(String s) {
        return s == null ? "" : s;
    }

    // 1. Mapare de la Entitate la Response (pentru GET-uri)
    public JobResponse mapJobToJobResponse(Job job) {
        Objects.requireNonNull(job, "Job entity is null");

        return new JobResponse(
                job.getId(),
                nvl(job.getTitle()),
                nvl(job.getDescription()),
                job.getHourlyRate(),
                job.getStartTime(),
                job.getEndTime(),
                nvl(job.getLocation()),
                job.getStatus(),
                job.getEmployer() != null ? job.getEmployer().getId() : null,
                job.getEmployer() != null ? nvl(job.getEmployer().getCompanyName()) : ""
        );
    }

    // 2. Mapare de la DTO la Entitate (pentru POST/CREATE)
    public Job mapJobDtoToJob(JobDto dto) {
        Objects.requireNonNull(dto, "Job DTO is null");

        Job job = new Job();
        job.setTitle(dto.title());
        job.setDescription(dto.description());
        job.setHourlyRate(dto.hourlyRate());
        job.setStartTime(dto.startTime());
        job.setEndTime(dto.endTime());
        job.setLocation(dto.location());
        // Statusul default OPEN este deja setat în entitate
        return job;
    }

    // 3. Mapare pentru liste de joburi
    public List<JobResponse> mapJobListToJobResponseList(List<Job> jobs) {
        if (jobs == null) return List.of();
        return jobs.stream()
                .filter(Objects::nonNull)
                .map(this::mapJobToJobResponse)
                .collect(Collectors.toList());
    }


    public JobListResponse mapJobListToJobListResponse(List<Job> jobs) {
        List<JobResponse> jobResponseList = mapJobListToJobResponseList(jobs);
        return new JobListResponse(jobResponseList);
    }
}