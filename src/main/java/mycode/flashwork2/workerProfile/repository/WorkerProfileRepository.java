package mycode.flashwork2.workerProfile.repository;

import mycode.flashwork2.workerProfile.models.WorkerProfile;
import mycode.flashwork2.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WorkerProfileRepository extends JpaRepository<WorkerProfile, Long> {
    Optional<WorkerProfile> findByUser(User user);
    void deleteByUser(User user);
}