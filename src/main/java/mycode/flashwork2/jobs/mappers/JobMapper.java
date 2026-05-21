package mycode.flashwork2.jobs.mappers;

import mycode.flashwork2.enrollment.models.EnrollmentStatus;
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

    public JobResponse mapJobToJobResponse(Job job) {
        Objects.requireNonNull(job, "Job entity is null");

        // numărăm câți applicanți au fost acceptați
        int acceptedCount = (job.getEnrollments() == null) ? 0 :
                (int) job.getEnrollments().stream()
                        .filter(e -> e.getStatus() == EnrollmentStatus.ACCEPTED)
                        .count();

        return new JobResponse(
                job.getId(),
                nvl(job.getTitle()),
                nvl(job.getDescription()),
                job.getHourlyRate(),
                job.getStartTime(),
                job.getEndTime(),
                nvl(job.getLocation()),
                job.getStatus(),
                job.getCategory(),
                job.getMaxWorkers(),
                acceptedCount,
                job.isRecurring(),
                job.getRecurrenceDays(),
                job.getEmployer() != null ? job.getEmployer().getUser().getId() : null,
                job.getEmployer() != null ? nvl(job.getEmployer().getCompanyName()) : ""
        );
    }

    public Job mapJobDtoToJob(JobDto dto) {
        Objects.requireNonNull(dto, "Job DTO is null");

        Job job = new Job();
        job.setTitle(dto.title());
        job.setDescription(dto.description());
        job.setHourlyRate(dto.hourlyRate());
        job.setStartTime(dto.startTime());
        job.setEndTime(dto.endTime());
        job.setLocation(dto.location());
        job.setCategory(dto.category());
        job.setMaxWorkers(dto.maxWorkers());
        job.setRecurring(dto.isRecurring());
        job.setRecurrenceDays(dto.recurrenceDays());

        return job;
    }

    public List<JobResponse> mapJobListToJobResponseList(List<Job> jobs) {
        if (jobs == null) return List.of();
        return jobs.stream()
                .filter(Objects::nonNull)
                .map(this::mapJobToJobResponse)
                .collect(Collectors.toList());
    }

    public JobListResponse mapJobListToJobListResponse(List<Job> jobs) {
        return new JobListResponse(mapJobListToJobResponseList(jobs));
    }
}