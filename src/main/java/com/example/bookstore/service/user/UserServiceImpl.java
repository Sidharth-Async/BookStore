package com.example.bookstore.service.user;

import com.example.bookstore.dto.user.UserRequest;
import com.example.bookstore.dto.user.UserResponse;
import com.example.bookstore.entities.User;
import com.example.bookstore.repositories.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private BCryptPasswordEncoder passwordEncoder = null;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse register(UserRequest request) {
        //valid or not
        if (userRepo.existsByUsername(request.getName())){
            throw new RuntimeException("Username already taken!");
        }
        if(userRepo.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already registered!");
        }

        //Hash the password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        //Create new User entity
        User user = new User();
        user.setUsername(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(hashedPassword.toCharArray());

        //save user in DB
        User savedUser = userRepo.save(user);

        //convert to response DTO
        return mapToResponse(savedUser);
    }

    private UserResponse mapToResponse(User user){
        UserResponse response = new UserResponse();
        response.setId((long) user.getId());
        response.setName(user.getUsername());
        response.setEmail(user.getEmail());
        return response;
    }

}
