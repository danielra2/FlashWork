package mycode.flashwork2.enrollment.service;

import mycode.flashwork2.enrollment.models.Enrollment;
import mycode.flashwork2.enrollment.models.EnrollmentStatus;
import mycode.flashwork2.enrollment.repository.EnrollmentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ClockOutScheduler {

    private final EnrollmentRepository enrollmentRepository;

    public ClockOutScheduler(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void autoClockOut() {
        List<Enrollment> active = enrollmentRepository.findByStatusAndClockInTimeIsNotNullAndClockOutTimeIsNull(EnrollmentStatus.ACCEPTED);

        LocalDateTime now = LocalDateTime.now();
        for (Enrollment e : active) {
            if (e.getJob().getEndTime() != null && e.getJob().getEndTime().isBefore(now)) {
                // use the job's official end time, not "now"
                e.setClockOutTime(e.getJob().getEndTime());
                e.setStatus(EnrollmentStatus.COMPLETED);
            }
        }
    }
}