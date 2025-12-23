package mycode.flashwork2.employerProfile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mycode.flashwork2.jobs.models.Job;
import mycode.flashwork2.users.models.User;

import java.util.List;

@Entity // OBLIGATORIU: Aceasta transformă clasa în entitate JPA
@Table(name = "employer_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Legătura către contul de login

    @Column(nullable = false)
    private String companyName;

    private String cui; // Codul Unic de Înregistrare

    private String description;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private List<Job> jobs; // Lista de joburi postate de această firmă
}

