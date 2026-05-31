package mycode.flashwork2.users.service;

import mycode.flashwork2.security.JwtUtil;
import mycode.flashwork2.users.dtos.AuthResponse;
import mycode.flashwork2.users.dtos.UserLoginRequest;
import mycode.flashwork2.users.exceptions.UserDoesntExistException;
import mycode.flashwork2.users.models.User;
import mycode.flashwork2.users.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserQueryServiceImpl(UserRepository userRepository,
                                PasswordEncoder passwordEncoder,
                                JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(UserDoesntExistException::new);

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Parola incorecta");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getUserType(), user.getId());

        return new AuthResponse(user.getId(), user.getEmail(), user.getUserType(), token);
    }
}