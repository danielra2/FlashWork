package mycode.flashwork2.enrollment.controller;

import lombok.RequiredArgsConstructor;
import mycode.flashwork2.enrollment.dto.EnrollmentResponse;
import mycode.flashwork2.enrollment.models.EnrollmentStatus;
import mycode.flashwork2.enrollment.service.EnrollmentCommandService;
import mycode.flashwork2.enrollment.service.EnrollmentQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentCommandService enrollmentCommandService;
    private final EnrollmentQueryService enrollmentQueryService;

    @PostMapping("/apply/{jobId}/{workerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public EnrollmentResponse applyToJob(@PathVariable Long jobId, @PathVariable Long workerId) {
        return enrollmentCommandService.applyToJob(jobId, workerId);
    }

    @PatchMapping("/{enrollmentId}/status")
    public EnrollmentResponse updateStatus(@PathVariable Long enrollmentId,
                                           @RequestParam EnrollmentStatus status) {
        return enrollmentCommandService.updateStatus(enrollmentId, status);
    }

    @DeleteMapping("/{enrollmentId}")
    public EnrollmentResponse cancelEnrollment(@PathVariable Long enrollmentId) {
        return enrollmentCommandService.cancelEnrollment(enrollmentId);
    }

    @GetMapping("/job/{jobId}")
    public List<EnrollmentResponse> getEnrollmentsByJob(@PathVariable Long jobId) {
        return enrollmentQueryService.getEnrollmentsByJob(jobId);
    }

    @GetMapping("/worker/{workerId}")
    public List<EnrollmentResponse> getEnrollmentsByWorker(@PathVariable Long workerId) {
        return enrollmentQueryService.getEnrollmentsByWorker(workerId);
    }
}