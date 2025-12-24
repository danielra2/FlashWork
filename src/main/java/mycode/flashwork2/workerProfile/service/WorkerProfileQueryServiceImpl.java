package mycode.flashwork2.workerProfile.service;

import lombok.RequiredArgsConstructor;
import mycode.flashwork2.users.exceptions.UserDoesntExistException;
import mycode.flashwork2.users.models.User;
import mycode.flashwork2.users.repository.UserRepository;
import mycode.flashwork2.workerProfile.dtos.WorkerProfileResponse;
import mycode.flashwork2.workerProfile.exceptions.WorkerProfileNotFoundException;
import mycode.flashwork2.workerProfile.mappers.WorkerProfileMapper;
import mycode.flashwork2.workerProfile.models.WorkerProfile;
import mycode.flashwork2.workerProfile.repository.WorkerProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkerProfileQueryServiceImpl implements WorkerProfileQueryService {

    private final WorkerProfileRepository workerProfileRepository;
    private final UserRepository userRepository;
    private final WorkerProfileMapper workerProfileMapper;

    public WorkerProfileQueryServiceImpl(WorkerProfileRepository workerProfileRepository,UserRepository userRepository,WorkerProfileMapper workerProfileMapper){
        this.userRepository=userRepository;
        this.workerProfileMapper=workerProfileMapper;
        this.workerProfileRepository=workerProfileRepository;

    }

    @Override
    @Transactional(readOnly = true)
    public WorkerProfileResponse getProfileByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow((UserDoesntExistException::new));
        WorkerProfile profile = workerProfileRepository.findByUser(user).orElseThrow(WorkerProfileNotFoundException::new);
        return workerProfileMapper.mapToResponse(profile);
    }
}