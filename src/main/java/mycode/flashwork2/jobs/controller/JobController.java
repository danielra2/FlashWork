package mycode.flashwork2.jobs.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mycode.flashwork2.jobs.dtos.JobDto;
import mycode.flashwork2.jobs.dtos.JobListResponse;
import mycode.flashwork2.jobs.dtos.JobResponse;
import mycode.flashwork2.jobs.service.JobCommandService;
import mycode.flashwork2.jobs.service.JobQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobCommandService jobCommandService;
    private final JobQueryService jobQueryService;

    @GetMapping
    public JobListResponse getAllJobs() {
        return jobQueryService.findAllJobs();
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public JobResponse createJob(Authentication authentication, @Valid @RequestBody JobDto jobDto) {
        String email = authentication.getName();
        return jobCommandService.createJob(email, jobDto);
    }
    @PutMapping("/put/{jobId}")
    public JobResponse updateJobPut(@PathVariable Long jobId, @Valid @RequestBody JobDto jobDto) {
        return jobCommandService.updateJobPut(jobId, jobDto);
    }
    @PatchMapping("/patch/{jobId}")
    public JobResponse updateJobPatch(@PathVariable Long jobId, @RequestBody JobDto jobDto) {
        return jobCommandService.updateJobPatch(jobId, jobDto);
    }
    @DeleteMapping("/delete/{jobId}")
    public JobResponse deleteJob(@PathVariable Long jobId) {
        return jobCommandService.deleteJob(jobId);
    }
}