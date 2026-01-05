package mycode.flashwork2.employerProfile.mappers;

import mycode.flashwork2.employerProfile.dtos.EmployerProfileResponse;
import mycode.flashwork2.employerProfile.models.EmployerProfile;
import org.springframework.stereotype.Component;

@Component
public class EmployerProfileMapper {

    public EmployerProfileResponse mapToResponse(EmployerProfile profile) {
        return new EmployerProfileResponse(
                profile.getId(),
                profile.getUser().getId(),
                profile.getCompanyName(),
                profile.getCui(),
                profile.getDescription()
        );
    }
}