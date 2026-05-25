package mycode.flashwork2.enrollment.repository;

import mycode.flashwork2.enrollment.models.Enrollment;
import mycode.flashwork2.enrollment.models.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    List<Enrollment> findByJobId(Long JobId);
    List<Enrollment>findByWorkerId(Long WorkerId);
    boolean existsByJobIdAndWorkerId(Long jobId, Long workerId);
    List<Enrollment> findAllByJobIdAndStatus(Long jobId, EnrollmentStatus status);
    List<Enrollment> findByStatusAndClockInTimeIsNotNullAndClockOutTimeIsNull(EnrollmentStatus status);



}
