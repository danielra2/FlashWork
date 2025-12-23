package mycode.flashwork2.workerProfile.models;

import jakarta.persistence.*;
import lombok.*;
import mycode.flashwork2.users.models.User;
import mycode.flashwork2.enrollment.models.Enrollment;
import java.util.List;

@Entity
@Table(name = "worker_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Legătura către contul de login

    private String firstName;
    private String lastName;
    private String phone;
    private String skills;
    private Double rating = 0.0;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments; // Aplicările acestui lucrător
}