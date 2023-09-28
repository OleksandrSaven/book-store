package com.app.onlinebookstore.service;

import com.app.onlinebookstore.dto.UserRegistrationRequestDto;
import com.app.onlinebookstore.dto.UserRegistrationResponseDto;
import com.app.onlinebookstore.exaption.RegistrationException;
import com.app.onlinebookstore.model.User;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException;

    User getAuthenticatedUser();
}
