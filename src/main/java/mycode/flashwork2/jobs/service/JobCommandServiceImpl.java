package mycode.flashwork2.jobs.service;

import jakarta.transaction.Transactional;
import mycode.flashwork2.employerProfile.EmployerProfile;
import mycode.flashwork2.employerProfile.EmployerProfileRepository;
import mycode.flashwork2.jobs.dtos.JobDto;
import mycode.flashwork2.jobs.dtos.JobResponse;
import mycode.flashwork2.jobs.exceptions.JobAlreadyExistsException;
import mycode.flashwork2.jobs.exceptions.JobDoesntExistException;
import mycode.flashwork2.jobs.mappers.JobMapper;
import mycode.flashwork2.jobs.models.Job;
import mycode.flashwork2.jobs.repository.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class JobCommandServiceImpl implements JobCommandService{

    private EmployerProfileRepository employerProfileRepository;
    private JobRepository jobRepository;
    private JobMapper jobMapper;

    public JobCommandServiceImpl(EmployerProfileRepository employerProfileRepository,JobRepository jobRepository,JobMapper jobMapper){
        this.employerProfileRepository=employerProfileRepository;
        this.jobRepository=jobRepository;
        this.jobMapper=jobMapper;
    }

    @Override
    public JobResponse createJob(Long employerId, JobDto jobDto) {
        EmployerProfile employer = employerProfileRepository.findById(employerId).orElseThrow(JobAlreadyExistsException::new);
        Job job = jobMapper.mapJobDtoToJob(jobDto);

        // Setam relația Many-to-One
        job.setEmployer(employer);

        //  Salvam jobul
        Job savedJob = jobRepository.save(job);

        //Returnam răspunsul mapat (Entity -> Response)
        return jobMapper.mapJobToJobResponse(savedJob);
    }
    @Transactional
    @Override
    public JobResponse updateJobPut(long id, JobDto jobDto) {
        Job existingJob = jobRepository.findById(id).orElseThrow((JobDoesntExistException::new));

        existingJob.setTitle(jobDto.title());
        existingJob.setDescription(jobDto.description());
        existingJob.setHourlyRate(jobDto.hourlyRate());
        existingJob.setStartTime(jobDto.startTime());
        existingJob.setEndTime(jobDto.endTime());
        existingJob.setLocation(jobDto.location());

        return jobMapper.mapJobToJobResponse(existingJob);
    }

    @Transactional
    @Override
    public JobResponse updateJobPatch(long id, JobDto jobDto) {
        Job existingJob = jobRepository.findById(id).orElseThrow((JobDoesntExistException::new));
        if (jobDto.title() != null) {
            existingJob.setTitle(jobDto.title());
        }
        if (jobDto.description() != null) {
            existingJob.setDescription(jobDto.description());
        }
        if (jobDto.hourlyRate()!=null) {
            existingJob.setHourlyRate(jobDto.hourlyRate());
        }
        if (jobDto.startTime() != null) {
            existingJob.setStartTime(jobDto.startTime());
        }
        if (jobDto.endTime() != null) {
            existingJob.setEndTime(jobDto.endTime());
        }
        if (jobDto.location() != null) {
            existingJob.setLocation(jobDto.location());
        }

        return jobMapper.mapJobToJobResponse(existingJob);
    }

    @Override
    public JobResponse deleteJob(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(JobDoesntExistException::new);
        JobResponse response = jobMapper.mapJobToJobResponse(job);
        jobRepository.delete(job);
        return response;
    }
}
