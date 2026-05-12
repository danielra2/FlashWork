package mycode.flashwork2.enrollment.service;

import mycode.flashwork2.enrollment.dto.EnrollmentResponse;

import java.util.List;

public interface EnrollmentQueryService {
    List<EnrollmentResponse> getEnrollmentsByJob(Long jobId);
    List<EnrollmentResponse> getEnrollmentsByWorker(Long workerId);
}
