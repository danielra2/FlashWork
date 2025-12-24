package mycode.flashwork2.workerProfile.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mycode.flashwork2.workerProfile.dtos.WorkerProfileDto;
import mycode.flashwork2.workerProfile.dtos.WorkerProfileResponse;
import mycode.flashwork2.workerProfile.service.WorkerProfileCommandService;
import mycode.flashwork2.workerProfile.service.WorkerProfileQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/worker-profiles")
@RequiredArgsConstructor
public class WorkerProfileController {

    private final WorkerProfileCommandService workerProfileCommandService;
    private final WorkerProfileQueryService workerProfileQueryService;

    // Obține profilul după ID-ul utilizatorului (folosit la încărcarea paginii de profil)
    @GetMapping("/user/{userId}")
    public ResponseEntity<WorkerProfileResponse> getProfileByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(workerProfileQueryService.getProfileByUserId(userId));
    }

    // Actualizează datele profilului (nume, telefon, skills)
    @PutMapping("/update/{userId}")
    public ResponseEntity<WorkerProfileResponse> updateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody WorkerProfileDto dto) {
        return ResponseEntity.ok(workerProfileCommandService.updateProfile(userId, dto));
    }

    // Actualizează rating-ul (metodă apelată de sistem după finalizarea unui job)
    @PatchMapping("/rating/{workerId}")
    public ResponseEntity<WorkerProfileResponse> updateRating(
            @PathVariable Long workerId,
            @RequestParam Double score) {
        return ResponseEntity.ok(workerProfileCommandService.updateRating(workerId, score));
    }
}