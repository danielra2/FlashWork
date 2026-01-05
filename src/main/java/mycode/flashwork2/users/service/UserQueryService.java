package mycode.flashwork2.users.service;

import mycode.flashwork2.users.dtos.UserLoginRequest;
import mycode.flashwork2.users.dtos.UserResponse;

public interface UserQueryService {
    UserResponse login(UserLoginRequest request);
}