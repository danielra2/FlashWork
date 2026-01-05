package mycode.flashwork2.employerProfile.service;

import lombok.RequiredArgsConstructor;


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
public class EmployerProfileQueryServiceImpl implements EmployerProfileQueryService {

    private final EmployerProfileRepository employerProfileRepository;
    private final UserRepository userRepository;
    private final EmployerProfileMapper employerProfileMapper;

    public EmployerProfileQueryServiceImpl(EmployerProfileRepository employerProfileRepository,UserRepository userRepository,EmployerProfileMapper employerProfileMapper){
        this.employerProfileMapper=employerProfileMapper;
        this.employerProfileRepository=employerProfileRepository;
        this.userRepository=userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public EmployerProfileResponse getProfileByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserDoesntExistException::new);
        // Trebuie să adaugi findByUser în EmployerProfileRepository
        EmployerProfile profile = employerProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profilul angajatorului nu a fost găsit"));
        return employerProfileMapper.mapToResponse(profile);
    }
}