package mycode.flashwork2.employerProfile.service;


import mycode.flashwork2.employerProfile.dtos.EmployerProfileDto;
import mycode.flashwork2.employerProfile.dtos.EmployerProfileResponse;
import mycode.flashwork2.employerProfile.mappers.EmployerProfileMapper;
import mycode.flashwork2.employerProfile.models.EmployerProfile;
import mycode.flashwork2.employerProfile.repository.EmployerProfileRepository;
import mycode.flashwork2.users.exceptions.UserDoesntExistException;
import mycode.flashwork2.users.models.User;
import mycode.flashwork2.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployerProfileCommandServiceImpl implements EmployerProfileCommandService {

    private final EmployerProfileRepository employerProfileRepository;
    private final UserRepository userRepository;
    private final EmployerProfileMapper employerProfileMapper;

    // Constructor manual pentru Dependency Injection
    public EmployerProfileCommandServiceImpl(EmployerProfileRepository employerProfileRepository, UserRepository userRepository, EmployerProfileMapper employerProfileMapper) {
        this.employerProfileRepository = employerProfileRepository;
        this.userRepository = userRepository;
        this.employerProfileMapper = employerProfileMapper;
    }

    @Override
    @Transactional
    public EmployerProfileResponse updateProfile(Long userId, EmployerProfileDto dto) {
        User user = userRepository.findById(userId).orElseThrow(UserDoesntExistException::new);
        EmployerProfile profile = employerProfileRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Profilul angajatorului nu a fost găsit"));
        profile.setCompanyName(dto.companyName());
        profile.setCui(dto.cui());
        profile.setDescription(dto.description());

        return employerProfileMapper.mapToResponse(profile);
    }
}