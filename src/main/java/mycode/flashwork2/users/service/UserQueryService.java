package mycode.flashwork2.users.service;

import mycode.flashwork2.users.dtos.AuthResponse;
import mycode.flashwork2.users.dtos.UserLoginRequest;

public interface UserQueryService {
    AuthResponse login(UserLoginRequest request);
}