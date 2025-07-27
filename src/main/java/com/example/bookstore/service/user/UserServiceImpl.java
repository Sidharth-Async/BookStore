package com.example.bookstore.service.user;

import com.example.bookstore.dto.user.UserRequest;
import com.example.bookstore.dto.user.UserResponse;
import com.example.bookstore.repositories.UserRepo;
import com.example.bookstore.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;

    }

    @Override
    public UserResponse register(UserRequest request){
        //valid or not
        if (userRepo.existsById()) {
            throw new RuntimeException("Username already taken!");
        }
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered!");
        }

    }
}
