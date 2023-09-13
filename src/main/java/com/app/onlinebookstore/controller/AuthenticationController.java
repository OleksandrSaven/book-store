package com.app.onlinebookstore.controller;

import com.app.onlinebookstore.dto.UserLoginRequestDto;
import com.app.onlinebookstore.dto.UserLoginResponseDto;
import com.app.onlinebookstore.dto.UserRegistrationRequestDto;
import com.app.onlinebookstore.dto.UserRegistrationResponseDto;
import com.app.onlinebookstore.exaption.RegistrationException;
import com.app.onlinebookstore.security.AuthenticationService;
import com.app.onlinebookstore.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserRegistrationResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
