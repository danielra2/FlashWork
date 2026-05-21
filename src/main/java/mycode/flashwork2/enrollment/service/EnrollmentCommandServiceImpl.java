package mycode.flashwork2.enrollment.service;

import jakarta.transaction.Transactional;
import mycode.flashwork2.enrollment.dto.EnrollmentResponse;
import mycode.flashwork2.enrollment.exceptions.EnrollmentAlreadyExistsException;
import mycode.flashwork2.enrollment.exceptions.EnrollmentNotFoundException;
import mycode.flashwork2.enrollment.mappers.EnrollmentMapper;
import mycode.flashwork2.enrollment.models.Enrollment;
import mycode.flashwork2.enrollment.models.EnrollmentStatus;
import mycode.flashwork2.enrollment.repository.EnrollmentRepository;
import mycode.flashwork2.jobs.exceptions.JobDoesntExistException;
import mycode.flashwork2.jobs.models.Job;
import mycode.flashwork2.jobs.models.JobStatus;
import mycode.flashwork2.jobs.repository.JobRepository;
import mycode.flashwork2.users.exceptions.UserDoesntExistException;
import mycode.flashwork2.users.models.User;
import mycode.flashwork2.users.repository.UserRepository;
import mycode.flashwork2.workerProfile.exceptions.WorkerProfileNotFoundException;
import mycode.flashwork2.workerProfile.models.WorkerProfile;
import mycode.flashwork2.workerProfile.repository.WorkerProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentCommandServiceImpl implements EnrollmentCommandService {

    private final EnrollmentRepository enrollmentRepository;
    private final JobRepository jobRepository;
    private final WorkerProfileRepository workerProfileRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final UserRepository userRepository;

    public EnrollmentCommandServiceImpl(EnrollmentRepository enrollmentRepository,JobRepository jobRepository, WorkerProfileRepository workerProfileRepository, EnrollmentMapper enrollmentMapper, UserRepository userRepository) {
        this.enrollmentRepository=enrollmentRepository;
        this.jobRepository=jobRepository;
        this.workerProfileRepository=workerProfileRepository;
        this.enrollmentMapper=enrollmentMapper;
        this.userRepository=userRepository;
    }

    @Override
    @Transactional
    public EnrollmentResponse applyToJob(Long jobId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserDoesntExistException::new);
        WorkerProfile worker = workerProfileRepository.findByUserId(user.getId())
                .orElseThrow(WorkerProfileNotFoundException::new);

        if (enrollmentRepository.existsByJobIdAndWorkerId(jobId, worker.getId())) {
            throw new EnrollmentAlreadyExistsException();
        }

        Job job = jobRepository.findById(jobId).orElseThrow(JobDoesntExistException::new);

        Enrollment enrollment = new Enrollment();
        enrollment.setJob(job);
        enrollment.setWorker(worker);

        Enrollment saved = enrollmentRepository.save(enrollment);
        return enrollmentMapper.mapToResponse(saved);
    }

    @Override
    @Transactional
    public EnrollmentResponse updateStatus(Long enrollmentId, EnrollmentStatus newStatus) {
        Enrollment enrollment=enrollmentRepository.findById(enrollmentId).orElseThrow(EnrollmentNotFoundException::new);
        enrollment.setStatus(newStatus);
        return enrollmentMapper.mapToResponse(enrollment);
    }

    @Override
    @Transactional
    public EnrollmentResponse cancelEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(EnrollmentNotFoundException::new);
        EnrollmentResponse response = enrollmentMapper.mapToResponse(enrollment);
        enrollmentRepository.delete(enrollment);
        return response;
    }

    @Override
    @Transactional
    public EnrollmentResponse completeEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(EnrollmentNotFoundException::new);
        enrollment.setStatus(EnrollmentStatus.COMPLETED);
        Job job = enrollment.getJob();
        job.setStatus(JobStatus.COMPLETED);
        jobRepository.save(job);

        return enrollmentMapper.mapToResponse(enrollment);
    }
}