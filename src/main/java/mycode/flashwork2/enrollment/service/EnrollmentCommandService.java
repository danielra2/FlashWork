package mycode.flashwork2.enrollment.service;

import mycode.flashwork2.enrollment.dto.EnrollmentResponse;
import mycode.flashwork2.enrollment.models.EnrollmentStatus;

public interface EnrollmentCommandService {
    EnrollmentResponse applyToJob(Long jobId, Long workerId);
    EnrollmentResponse updateStatus(Long enrollmentId, EnrollmentStatus newStatus);
    EnrollmentResponse cancelEnrollment(Long enrollmentId);
    EnrollmentResponse completeEnrollment(Long enrollmentId);
}