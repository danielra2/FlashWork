package mycode.flashwork2.jobs.dtos;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record JobDto(
        @NotBlank(message = "Titlul nu poate fi gol")
        @Size(max = 255)
        String title,

        @NotBlank(message = "Descrierea nu poate fi goala")
        String description,

        @NotNull(message = "Tariful orar este obligatoriu")
        @Positive(message = "Tariful orar trebuie să fie mai mare decât 0")
        Double hourlyRate,

        @NotNull(message = "Data de început este obligatorie")
        LocalDateTime startTime,

        @NotNull(message = "Data de final este obligatorie")
        LocalDateTime endTime,

        @NotBlank(message = "Locația este obligatorie")
        String location
) {}