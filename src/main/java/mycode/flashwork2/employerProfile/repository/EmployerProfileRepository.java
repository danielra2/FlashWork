package mycode.flashwork2.employerProfile.repository;

import mycode.flashwork2.employerProfile.models.EmployerProfile;
import mycode.flashwork2.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerProfileRepository extends JpaRepository<EmployerProfile,Long> {
    Optional<EmployerProfile> findByUser(User user);
}
