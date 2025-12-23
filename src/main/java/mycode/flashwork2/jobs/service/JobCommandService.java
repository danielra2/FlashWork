package mycode.flashwork2.jobs.service;

import mycode.flashwork2.jobs.dtos.JobDto;
import mycode.flashwork2.jobs.dtos.JobResponse;

public interface JobCommandService {
    JobResponse createJob(Long employerId, JobDto jobDto);
}
