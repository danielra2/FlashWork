package mycode.flashwork2.jobs.dtos;

import jakarta.validation.constraints.*;
import mycode.flashwork2.jobs.models.JobCategory;
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
        String location,

        JobCategory category,

        @Min(value = 1, message = "Trebuie cel puțin un loc")
        Integer maxWorkers,

        Boolean isRecurring,

        @Min(value = 1, message = "Intervalul minim este 1 zi")
        Integer recurrenceDays
) {}