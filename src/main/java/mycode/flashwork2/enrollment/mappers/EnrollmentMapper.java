package mycode.flashwork2.enrollment.mappers;

import mycode.flashwork2.enrollment.dto.EnrollmentResponse;
import mycode.flashwork2.enrollment.models.Enrollment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EnrollmentMapper {

    public EnrollmentResponse mapToResponse(Enrollment enrollment) {
        Objects.requireNonNull(enrollment, "Enrollment entity is null");
        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getJob().getId(),
                enrollment.getJob().getTitle(),
                enrollment.getJob().getStartTime(),   // needed for frontend countdown
                enrollment.getJob().getEndTime(),     // needed for frontend countdown
                enrollment.getWorker().getId(),
                enrollment.getWorker().getFirstName(),
                enrollment.getWorker().getLastName(),
                enrollment.getWorker().getSkills(),   // skill badges for employer
                enrollment.getAppliedAt(),
                enrollment.getStatus(),
                enrollment.getClockInTime(),          // null until worker clocks in
                enrollment.getClockOutTime()          // null until worker clocks out
        );
    }
}