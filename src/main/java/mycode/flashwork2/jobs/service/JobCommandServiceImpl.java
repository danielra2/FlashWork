package mycode.flashwork2.jobs.service;

import jakarta.transaction.Transactional;
import mycode.flashwork2.employerProfile.models.EmployerProfile;
import mycode.flashwork2.employerProfile.repository.EmployerProfileRepository;
import mycode.flashwork2.jobs.dtos.JobDto;
import mycode.flashwork2.jobs.dtos.JobResponse;
import mycode.flashwork2.jobs.exceptions.JobDoesntExistException;
import mycode.flashwork2.jobs.mappers.JobMapper;
import mycode.flashwork2.jobs.models.Job;
import mycode.flashwork2.jobs.repository.JobRepository;
import mycode.flashwork2.employerProfile.exceptions.EmployerProfileNotFoundException;
import mycode.flashwork2.users.exceptions.UserDoesntExistException;
import mycode.flashwork2.users.models.User;
import mycode.flashwork2.users.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class JobCommandServiceImpl implements JobCommandService {

    private EmployerProfileRepository employerProfileRepository;
    private JobRepository jobRepository;
    private JobMapper jobMapper;
    private UserRepository userRepository;

    public JobCommandServiceImpl(EmployerProfileRepository employerProfileRepository,
                                 JobRepository jobRepository,
                                 JobMapper jobMapper,
                                 UserRepository userRepository) {
        this.employerProfileRepository = employerProfileRepository;
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.userRepository = userRepository;
    }

    @Override
    public JobResponse createJob(String email, JobDto jobDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserDoesntExistException::new);
        EmployerProfile employer = employerProfileRepository.findByUserId(user.getId())
                .orElseThrow(EmployerProfileNotFoundException::new);
        Job job = jobMapper.mapJobDtoToJob(jobDto);
        job.setEmployer(employer);
        Job savedJob = jobRepository.save(job);
        return jobMapper.mapJobToJobResponse(savedJob);
    }

    @Transactional
    @Override
    public JobResponse updateJobPut(long id, JobDto jobDto) {
        Job existingJob = jobRepository.findById(id).orElseThrow(JobDoesntExistException::new);

        existingJob.setTitle(jobDto.title());
        existingJob.setDescription(jobDto.description());
        existingJob.setHourlyRate(jobDto.hourlyRate());
        existingJob.setStartTime(jobDto.startTime());
        existingJob.setEndTime(jobDto.endTime());
        existingJob.setLocation(jobDto.location());
        existingJob.setCategory(jobDto.category());
        existingJob.setMaxWorkers(jobDto.maxWorkers());
        existingJob.setRecurring(jobDto.isRecurring() != null && jobDto.isRecurring());
        existingJob.setRecurrenceDays(jobDto.recurrenceDays());

        return jobMapper.mapJobToJobResponse(existingJob);
    }

    @Transactional
    @Override
    public JobResponse updateJobPatch(long id, JobDto jobDto) {
        Job existingJob = jobRepository.findById(id).orElseThrow(JobDoesntExistException::new);

        if (jobDto.title() != null)       existingJob.setTitle(jobDto.title());
        if (jobDto.description() != null) existingJob.setDescription(jobDto.description());
        if (jobDto.hourlyRate() != null)  existingJob.setHourlyRate(jobDto.hourlyRate());
        if (jobDto.startTime() != null)   existingJob.setStartTime(jobDto.startTime());
        if (jobDto.endTime() != null)     existingJob.setEndTime(jobDto.endTime());
        if (jobDto.location() != null)    existingJob.setLocation(jobDto.location());
        if (jobDto.category() != null)    existingJob.setCategory(jobDto.category());
        if (jobDto.maxWorkers() != null)  existingJob.setMaxWorkers(jobDto.maxWorkers());
        if (jobDto.recurrenceDays() != null) existingJob.setRecurrenceDays(jobDto.recurrenceDays());
        existingJob.setRecurring(jobDto.isRecurring() != null && jobDto.isRecurring());

        return jobMapper.mapJobToJobResponse(existingJob);
    }

    @Transactional
    @Override
    public JobResponse deleteJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobDoesntExistException::new);
        JobResponse response = jobMapper.mapJobToJobResponse(job);
        jobRepository.delete(job);
        return response;
    }
}