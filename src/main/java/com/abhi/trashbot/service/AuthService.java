package com.abhi.trashbot.service;

import org.springframework.stereotype.Service;

import com.abhi.trashbot.dto.LoginRequest;
import com.abhi.trashbot.dto.SignupRequest;
import com.abhi.trashbot.model.User;
import com.abhi.trashbot.repository.UserRepository;




@Service
public class AuthService {

    private UserRepository userRepository;

    // Constructor Injection (IMPORTANT)
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signup(SignupRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getInstitution(),
                request.getLocation()
        );

        return userRepository.save(user);
    }

    public User login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
