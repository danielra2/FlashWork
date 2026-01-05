package mycode.flashwork2.employerProfile.service;

import mycode.flashwork2.employerProfile.dtos.EmployerProfileResponse;

public interface EmployerProfileQueryService {
    EmployerProfileResponse getProfileByUserId(Long userId);
}