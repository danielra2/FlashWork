package mycode.flashwork2.employerProfile.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mycode.flashwork2.employerProfile.dtos.EmployerProfileDto;
import mycode.flashwork2.employerProfile.dtos.EmployerProfileResponse;
import mycode.flashwork2.employerProfile.service.EmployerProfileCommandService;
import mycode.flashwork2.employerProfile.service.EmployerProfileQueryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employer-profiles")
@RequiredArgsConstructor
public class EmployerProfileController {

    private final EmployerProfileCommandService employerProfileCommandService;
    private final EmployerProfileQueryService employerProfileQueryService;

    @GetMapping("/user/{userId}")
    public EmployerProfileResponse getProfile(@PathVariable Long userId) {
        return employerProfileQueryService.getProfileByUserId(userId);
    }

    @PutMapping("/update/{userId}")
    public EmployerProfileResponse updateProfile(@PathVariable Long userId,@Valid @RequestBody EmployerProfileDto dto) {
        return employerProfileCommandService.updateProfile(userId, dto);
    }
}