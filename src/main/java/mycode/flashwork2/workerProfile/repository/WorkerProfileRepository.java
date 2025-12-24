package mycode.flashwork2.workerProfile.repository;

import mycode.flashwork2.workerProfile.models.WorkerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerProfileRepository extends JpaRepository<WorkerProfile,Long> {
}
