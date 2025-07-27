package com.example.bookstore.service.user;

import com.example.bookstore.dto.user.UserResponse;
import com.example.bookstore.service.user.UserServiceImpl;

public interface UserService {
    UserResponse register(UserRegisterRequest request);
    String login(UserLoginRequest request);

}
