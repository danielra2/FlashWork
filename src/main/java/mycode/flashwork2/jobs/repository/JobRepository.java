package mycode.flashwork2.jobs.repository;

import mycode.flashwork2.jobs.models.Job;
import mycode.flashwork2.jobs.models.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByRecurringTrueAndStatusAndStartTimeBefore(
            JobStatus status,
            LocalDateTime dateTime
    );
}