package mycode.flashwork2.enrollment.mappers;

import mycode.flashwork2.enrollment.dto.EnrollmentResponse;
import mycode.flashwork2.enrollment.models.Enrollment;
import mycode.flashwork2.employerProfile.models.EmployerProfile;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EnrollmentMapper {

    public EnrollmentResponse mapToResponse(Enrollment enrollment) {
        Objects.requireNonNull(enrollment, "Enrollment entity is null");

        EmployerProfile employer = enrollment.getJob().getEmployer();
        String companyName = (employer != null && employer.getCompanyName() != null)
                ? employer.getCompanyName() : "";

        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getJob().getId(),
                enrollment.getJob().getTitle(),
                companyName,
                enrollment.getJob().getStartTime(),
                enrollment.getJob().getEndTime(),
                enrollment.getWorker().getId(),
                enrollment.getWorker().getFirstName(),
                enrollment.getWorker().getLastName(),
                enrollment.getWorker().getSkills(),
                enrollment.getAppliedAt(),
                enrollment.getStatus(),
                enrollment.getClockInTime(),
                enrollment.getClockOutTime()
        );
    }
}