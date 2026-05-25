        package mycode.flashwork2.workerProfile.dtos;

        import jakarta.validation.constraints.NotBlank;
        import mycode.flashwork2.workerProfile.models.WorkerSkill;

        import java.util.List;

        public record WorkerProfileDto(
                @NotBlank(message = "Prenumele este obligatoriu")
                String firstName,
                @NotBlank(message = "Numele este obligatoriu")
                String lastName,
                String phone,
                List<WorkerSkill> skills
        ) {}