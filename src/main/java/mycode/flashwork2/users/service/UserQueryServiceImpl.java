package mycode.flashwork2.users.service;

import mycode.flashwork2.users.dtos.UserLoginRequest;
import mycode.flashwork2.users.dtos.UserResponse;
import mycode.flashwork2.users.exceptions.UserDoesntExistException;
import mycode.flashwork2.users.mappers.UserMapper;
import mycode.flashwork2.users.models.User;
import mycode.flashwork2.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserQueryServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(UserDoesntExistException::new);
        if (!user.getPassword().equals(request.password())) {
            throw new RuntimeException("Parolă incorectă!");
        }
        return userMapper.mapUserToUserResponse(user);
    }
}