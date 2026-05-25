package mycode.flashwork2.workerProfile.models;

import jakarta.persistence.*;
import lombok.*;
import mycode.flashwork2.users.models.User;
import mycode.flashwork2.enrollment.models.Enrollment;
import java.util.ArrayList;
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
    private User user;

    private String firstName;
    private String lastName;
    private String phone;

    // @ElementCollection = store the list in a separate table automatically
    // @Enumerated(STRING) = store the enum name as text, not a number
    // @CollectionTable = defines the table name and the foreign key column
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "worker_skills", joinColumns = @JoinColumn(name = "worker_id"))
    @Column(name = "skill")
    private List<WorkerSkill> skills = new ArrayList<>();

    private Double rating = 0.0;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;
}