package mycode.flashwork2.jobs.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mycode.flashwork2.jobs.dtos.JobDto;
import mycode.flashwork2.jobs.dtos.JobListResponse;
import mycode.flashwork2.jobs.dtos.JobResponse;
import mycode.flashwork2.jobs.service.JobCommandService;
import mycode.flashwork2.jobs.service.JobQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobCommandService jobCommandService;
    private final JobQueryService jobQueryService;

    // Obtine toate joburile
    @GetMapping
    public JobListResponse getAllJobs() {
        return jobQueryService.findAllJobs();
    }

    // Creare Job (asociat unui angajator)
    @PostMapping("/{employerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public JobResponse createJob(@PathVariable Long employerId, @Valid @RequestBody JobDto jobDto) {
        return jobCommandService.createJob(employerId, jobDto);
    }

    // Update Complet (PUT)
    @PutMapping("/{jobId}")
    public JobResponse updateJobPut(@PathVariable Long jobId, @Valid @RequestBody JobDto jobDto) {
        return jobCommandService.updateJobPut(jobId, jobDto);
    }

    // Update Partial (PATCH)
    @PatchMapping("/{jobId}")
    public JobResponse updateJobPatch(@PathVariable Long jobId, @RequestBody JobDto jobDto) {
        return jobCommandService.updateJobPatch(jobId, jobDto);
    }

    // Stergere Job (returneaza obiectul sters)
    @DeleteMapping("/{jobId}")
    public JobResponse deleteJob(@PathVariable Long jobId) {
        return jobCommandService.deleteJob(jobId);
    }
}