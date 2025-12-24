package mycode.flashwork2.workerProfile.mappers;

import mycode.flashwork2.workerProfile.dtos.WorkerProfileResponse;
import mycode.flashwork2.workerProfile.models.WorkerProfile;
import org.springframework.stereotype.Component;

@Component
public class WorkerProfileMapper {

    public WorkerProfileResponse mapToResponse(WorkerProfile profile) {
        return new WorkerProfileResponse(
                profile.getId(),
                profile.getUser().getId(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getPhone(),
                profile.getSkills(),
                profile.getRating()
        );
    }
}