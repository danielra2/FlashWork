package mycode.flashwork2.jobs.service;

import mycode.flashwork2.employerProfile.EmployerProfile;
import mycode.flashwork2.employerProfile.EmployerProfileRepository;
import mycode.flashwork2.jobs.dtos.JobDto;
import mycode.flashwork2.jobs.dtos.JobResponse;
import mycode.flashwork2.jobs.mappers.JobMapper;
import mycode.flashwork2.jobs.models.Job;
import mycode.flashwork2.jobs.repository.JobRepository;

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
        EmployerProfile employer = employerProfileRepository.findById(employerId).orElseThrow(() -> new RuntimeException("Angajatorul cu id-ul " + employerId + " nu există"));
        Job job = jobMapper.mapJobDtoToJob(jobDto);

        // Setam relația Many-to-One
        job.setEmployer(employer);

        //  Salvam jobul
        Job savedJob = jobRepository.save(job);

        //Returnam răspunsul mapat (Entity -> Response)
        return jobMapper.mapJobToJobResponse(savedJob);
    }
}
