package mycode.flashwork2.jobs.service;

import mycode.flashwork2.jobs.models.Job;
import mycode.flashwork2.jobs.models.JobStatus;
import mycode.flashwork2.jobs.repository.JobRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JobRecurrenceScheduler {

    private final JobRepository jobRepository;

    public JobRecurrenceScheduler(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void createNextOccurrences() {
        List<Job> expiredRecurringJobs = jobRepository
                .findByRecurringTrueAndStatusAndStartTimeBefore(
                        JobStatus.OPEN,
                        LocalDateTime.now()
                );

        for (Job oldJob : expiredRecurringJobs) {
            int days = oldJob.getRecurrenceDays() != null ? oldJob.getRecurrenceDays() : 1;

            Job nextJob = new Job();
            nextJob.setTitle(oldJob.getTitle());
            nextJob.setDescription(oldJob.getDescription());
            nextJob.setHourlyRate(oldJob.getHourlyRate());
            nextJob.setStartTime(oldJob.getStartTime().plusDays(days));
            nextJob.setEndTime(oldJob.getEndTime().plusDays(days));
            nextJob.setLocation(oldJob.getLocation());
            nextJob.setCategory(oldJob.getCategory());
            nextJob.setMaxWorkers(oldJob.getMaxWorkers());
            nextJob.setRecurring(true);
            nextJob.setRecurrenceDays(days);
            nextJob.setEmployer(oldJob.getEmployer());

            jobRepository.save(nextJob);

            oldJob.setStatus(JobStatus.COMPLETED);
            jobRepository.save(oldJob);
        }
    }
}