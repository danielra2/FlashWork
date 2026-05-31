package mycode.flashwork2.workerProfile.service;

import jakarta.transaction.Transactional;
import mycode.flashwork2.users.exceptions.UserDoesntExistException;
import mycode.flashwork2.users.models.User;
import mycode.flashwork2.users.repository.UserRepository;
import mycode.flashwork2.workerProfile.dtos.WorkerProfileDto;
import mycode.flashwork2.workerProfile.dtos.WorkerProfileResponse;
import mycode.flashwork2.workerProfile.exceptions.InvalidRatingException;
import mycode.flashwork2.workerProfile.exceptions.WorkerProfileNotFoundException;
import mycode.flashwork2.workerProfile.mappers.WorkerProfileMapper;
import mycode.flashwork2.workerProfile.models.WorkerProfile;
import mycode.flashwork2.workerProfile.repository.WorkerProfileRepository;
import org.springframework.stereotype.Service;


@Service
public class WorkerProfileCommandServiceImpl implements WorkerProfileCommandService {

    private WorkerProfileRepository workerProfileRepository;
    private WorkerProfileMapper workerProfileMapper;
    private UserRepository userRepository;

    public WorkerProfileCommandServiceImpl(WorkerProfileRepository workerProfileRepository, WorkerProfileMapper workerProfileMapper, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.workerProfileRepository = workerProfileRepository;
        this.workerProfileMapper = workerProfileMapper;
    }

    @Override
    @Transactional
    public WorkerProfileResponse updateProfile(Long userId, WorkerProfileDto dto) {
        User user = userRepository.findById(userId).orElseThrow(UserDoesntExistException::new);

        WorkerProfile profile = workerProfileRepository.findByUser(user).orElseThrow(WorkerProfileNotFoundException::new);

        profile.setFirstName(dto.firstName());
        profile.setLastName(dto.lastName());
        profile.setPhone(dto.phone());
        profile.setSkills(dto.skills());

        return workerProfileMapper.mapToResponse(profile);
    }

    @Override
    @Transactional
    public WorkerProfileResponse updateRating(Long workerId, Double newRatingScore) {
        WorkerProfile profile = workerProfileRepository.findById(workerId).orElseThrow(WorkerProfileNotFoundException::new);
        if (newRatingScore<1.0||newRatingScore > 5.0) {
            throw new InvalidRatingException();
        }

        profile.setRating(newRatingScore);
        return workerProfileMapper.mapToResponse(profile);
    }
}
