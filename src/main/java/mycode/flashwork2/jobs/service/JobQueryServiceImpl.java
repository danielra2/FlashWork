package mycode.flashwork2.jobs.service;

import jakarta.transaction.Transactional;
import mycode.flashwork2.jobs.dtos.JobListResponse;
import mycode.flashwork2.jobs.dtos.JobResponse;
import mycode.flashwork2.jobs.mappers.JobMapper;
import mycode.flashwork2.jobs.models.Job;
import mycode.flashwork2.jobs.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class JobQueryServiceImpl implements JobQueryService{
    private JobRepository jobRepository;
    private JobMapper jobMapper;

    public JobQueryServiceImpl(JobRepository jobRepository,JobMapper jobMapper){
        this.jobRepository=jobRepository;
        this.jobMapper=jobMapper;
    }

    @Override
    public JobListResponse findAllJobs() {
        List<Job>jobs=jobRepository.findAll();
        List<JobResponse>jobResponses=jobMapper.mapJobListToJobResponseList(jobs);
        return new JobListResponse(jobResponses);

    }
}
