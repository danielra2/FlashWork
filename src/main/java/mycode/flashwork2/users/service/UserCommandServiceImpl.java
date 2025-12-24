package mycode.flashwork2.users.service;

import jakarta.transaction.Transactional;
import mycode.flashwork2.employerProfile.EmployerProfile;
import mycode.flashwork2.employerProfile.EmployerProfileRepository;
import mycode.flashwork2.users.dtos.UserRegistrationRequest;
import mycode.flashwork2.users.dtos.UserResponse;
import mycode.flashwork2.users.exceptions.EmailAlreadyInUse;
import mycode.flashwork2.users.exceptions.UserDoesntExistException;
import mycode.flashwork2.users.mappers.UserMapper;
import mycode.flashwork2.users.models.User;
import mycode.flashwork2.users.models.UserType;
import mycode.flashwork2.users.repository.UserRepository;
import mycode.flashwork2.workerProfile.models.WorkerProfile;
import mycode.flashwork2.workerProfile.repository.WorkerProfileRepository;
import org.springframework.stereotype.Service;


@Service
public class UserCommandServiceImpl implements UserCommandService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private WorkerProfileRepository workerProfileRepository;
    private EmployerProfileRepository employerProfileRepository;

    public UserCommandServiceImpl(UserRepository userRepository,UserMapper userMapper,WorkerProfileRepository workerProfileRepository,EmployerProfileRepository employerProfileRepository){
        this.userRepository=userRepository;
        this.userMapper=userMapper;
        this.employerProfileRepository=employerProfileRepository;
        this.workerProfileRepository=workerProfileRepository;
    }

    @Override
    public UserResponse registerUser(UserRegistrationRequest request) {
        if(userRepository.existsByEmail(request.email())){
            throw new EmailAlreadyInUse();

        }
        User user = userMapper.mapRegistrationRequestToUser(request);
        User savedUser = userRepository.save(user);

        // Creare profil automat
        if (savedUser.getUserType() == UserType.WORKER) {
            WorkerProfile workerProfile = new WorkerProfile();
            workerProfile.setUser(savedUser);
            workerProfileRepository.save(workerProfile);
        } else {
            EmployerProfile employerProfile = new EmployerProfile();
            employerProfile.setUser(savedUser);
            employerProfile.setCompanyName(""); // Camp obligatoriu în DB, trebuie inițializat
            employerProfileRepository.save(employerProfile);
        }

        return userMapper.mapUserToUserResponse(savedUser);

    }
    @Transactional
    @Override
    public UserResponse deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserDoesntExistException::new);
        UserResponse response = userMapper.mapUserToUserResponse(user);

        // Ștergem utilizatorul (Cascade va șterge profilele)
        userRepository.delete(user);

        return response;
    }

    @Transactional
    @Override
    public UserResponse updatePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(UserDoesntExistException::new);

        if (newPassword == null || newPassword.length() < 6) {
            throw new RuntimeException("Parola este prea scurtă");
        }

        user.setPassword(newPassword);
        // Salvarea este automata datorită @Transactional

        return userMapper.mapUserToUserResponse(user);
    }
}

