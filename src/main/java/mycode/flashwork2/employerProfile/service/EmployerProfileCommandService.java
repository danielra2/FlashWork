package mycode.flashwork2.employerProfile.service;

import mycode.flashwork2.employerProfile.dtos.EmployerProfileDto;
import mycode.flashwork2.employerProfile.dtos.EmployerProfileResponse;

public interface EmployerProfileCommandService {
    EmployerProfileResponse updateProfile(Long userId, EmployerProfileDto dto);
}