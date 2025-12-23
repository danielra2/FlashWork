package mycode.flashwork2.enrollment.models;

import jakarta.persistence.*;
import lombok.*;
import mycode.flashwork2.jobs.models.Job;
import mycode.flashwork2.workerProfile.models.WorkerProfile;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job; // Jobul la care s-a aplicat

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private WorkerProfile worker; // Cine a aplicat

    private LocalDateTime appliedAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status = EnrollmentStatus.PENDING; // PENDING,ACCEPTED,REJECTED
}