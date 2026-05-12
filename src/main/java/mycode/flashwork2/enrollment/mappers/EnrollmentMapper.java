package mycode.flashwork2.enrollment.mappers;

import mycode.flashwork2.enrollment.dto.EnrollmentResponse;
import mycode.flashwork2.enrollment.models.Enrollment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EnrollmentMapper {


    public EnrollmentResponse mapToResponse(Enrollment enrollment) {
        Objects.requireNonNull(enrollment,"Enrollment entity is null");
        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getJob().getId(),
                enrollment.getJob().getTitle(),
                enrollment.getWorker().getId(),
                enrollment.getWorker().getFirstName(),
                enrollment.getWorker().getLastName(),
                enrollment.getAppliedAt(),
                enrollment.getStatus()
        );
    }
}