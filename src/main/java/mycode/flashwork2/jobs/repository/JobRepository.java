package mycode.flashwork2.jobs.repository;

import mycode.flashwork2.jobs.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findAll();

}
