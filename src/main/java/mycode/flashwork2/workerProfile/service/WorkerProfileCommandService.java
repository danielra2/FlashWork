package mycode.flashwork2.workerProfile.service;

import mycode.flashwork2.workerProfile.dtos.WorkerProfileDto;
import mycode.flashwork2.workerProfile.dtos.WorkerProfileResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerProfileCommandService {
    WorkerProfileResponse updateProfile(Long userId, WorkerProfileDto dto);
    WorkerProfileResponse updateRating(Long workerId, Double newRatingScore);

}
