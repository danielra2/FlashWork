package mycode.flashwork2.jobs.models;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import mycode.flashwork2.employerProfile.EmployerProfile;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="job_post")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private EmployerProfile employer;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "hourly_rate")
    private Double hourlyRate;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    private String location;

    @Enumerated(EnumType.STRING)
    private JobStatus status = JobStatus.OPEN; // OPEN, FILLED, COMPLETED


}
