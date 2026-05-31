package mycode.flashwork2.jobs.repository;

import mycode.flashwork2.jobs.models.Job;
import mycode.flashwork2.jobs.models.JobCategory;
import mycode.flashwork2.jobs.models.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByRecurringTrueAndStatusAndStartTimeBefore(
            JobStatus status,
            LocalDateTime dateTime
    );
    @Query("SELECT j FROM Job j WHERE " +
            "(:category IS NULL OR j.category = :category) AND " +
            "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%')))")
    List<Job> findByFilters(@Param("category") JobCategory category,
                            @Param("location") String location);
}