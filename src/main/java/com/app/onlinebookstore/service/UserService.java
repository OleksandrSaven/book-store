package com.app.onlinebookstore.service;

import com.app.onlinebookstore.dto.UserRegistrationRequestDto;
import com.app.onlinebookstore.dto.UserRegistrationResponseDto;
import com.app.onlinebookstore.exaption.RegistrationException;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException;
}
