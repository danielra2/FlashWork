package mycode.flashwork2.jobs.service;

import mycode.flashwork2.jobs.dtos.JobDto;
import mycode.flashwork2.jobs.dtos.JobResponse;

public interface JobCommandService {
    JobResponse createJob(Long employerId, JobDto jobDto);
    JobResponse updateJobPut(long id, JobDto jobDto);
    JobResponse updateJobPatch(long id, JobDto jobDto);
    JobResponse deleteJob(Long jobId);
}
