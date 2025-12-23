package mycode.flashwork2.jobs.models;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import mycode.flashwork2.employerProfile.EmployerProfile;
import mycode.flashwork2.enrollment.models.Enrollment;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments;

    @Override
    public String toString(){
        String text="Title: "+title+" Descroption: "+description+" Hourly Rate"+hourlyRate;
        return text;
    }


}
