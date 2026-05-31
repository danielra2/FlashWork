package mycode.flashwork2.jobs.service;

import mycode.flashwork2.jobs.dtos.JobListResponse;
import mycode.flashwork2.jobs.dtos.JobResponse;
import mycode.flashwork2.jobs.mappers.JobMapper;
import mycode.flashwork2.jobs.models.Job;
import mycode.flashwork2.jobs.models.JobCategory;
import mycode.flashwork2.jobs.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobQueryServiceImpl implements JobQueryService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;


    public JobQueryServiceImpl(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public JobListResponse findAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        List<JobResponse> jobResponses = jobMapper.mapJobListToJobResponseList(jobs);
        return new JobListResponse(jobResponses);
    }

    @Override
    @Transactional(readOnly = true)
    public JobListResponse findFilteredJobs(String category, String location) {
        JobCategory cat = (category != null && !category.isBlank()) ? JobCategory.valueOf(category.toUpperCase()) : null;
        String loc = (location != null && location.isBlank()) ? null : location;
        List<Job> jobs = jobRepository.findByFilters(cat, loc);
        return new JobListResponse(jobMapper.mapJobListToJobResponseList(jobs));
    }
}