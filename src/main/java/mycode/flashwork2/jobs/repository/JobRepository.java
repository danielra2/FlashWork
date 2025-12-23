package mycode.flashwork2.jobs.repository;

import mycode.flashwork2.jobs.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findAll();

}
