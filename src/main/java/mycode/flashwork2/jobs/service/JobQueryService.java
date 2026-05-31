package mycode.flashwork2.jobs.service;

import mycode.flashwork2.jobs.dtos.JobListResponse;

public interface JobQueryService {
    JobListResponse findAllJobs();
    JobListResponse findFilteredJobs(String category, String location);

}
