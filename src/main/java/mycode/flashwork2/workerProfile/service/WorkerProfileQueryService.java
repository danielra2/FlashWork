package mycode.flashwork2.workerProfile.service;

import mycode.flashwork2.workerProfile.dtos.WorkerProfileResponse;

public interface WorkerProfileQueryService {
    WorkerProfileResponse getProfileByUserId(Long userId);
}