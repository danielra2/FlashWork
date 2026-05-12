package mycode.flashwork2.enrollment.service;


import mycode.flashwork2.enrollment.dto.EnrollmentResponse;
import mycode.flashwork2.enrollment.mappers.EnrollmentMapper;
import mycode.flashwork2.enrollment.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class EnrollmentQueryServiceImpl implements EnrollmentQueryService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentQueryServiceImpl(EnrollmentRepository enrollmentRepository, EnrollmentMapper enrollmentMapper){

        this.enrollmentRepository=enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getEnrollmentsByJob(Long jobId) {
        return enrollmentRepository.findByJobId(jobId).stream().map(enrollmentMapper::mapToResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponse> getEnrollmentsByWorker(Long workerId) {
        return enrollmentRepository.findByWorkerId(workerId).stream().map(enrollmentMapper::mapToResponse).toList();
    }
}
