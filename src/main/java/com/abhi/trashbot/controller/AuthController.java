package com.abhi.trashbot.controller;

import org.springframework.web.bind.annotation.*;

import com.abhi.trashbot.dto.LoginRequest;
import com.abhi.trashbot.dto.SignupRequest;
import com.abhi.trashbot.model.User;
import com.abhi.trashbot.service.AuthService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private AuthService authService;

    // Constructor Injection
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
