package com.app.onlinebookstore.service.impl;

import com.app.onlinebookstore.dto.UserRegistrationRequestDto;
import com.app.onlinebookstore.dto.UserRegistrationResponseDto;
import com.app.onlinebookstore.exaption.RegistrationException;
import com.app.onlinebookstore.mapper.UserMapper;
import com.app.onlinebookstore.model.Role;
import com.app.onlinebookstore.model.RoleName;
import com.app.onlinebookstore.model.ShoppingCart;
import com.app.onlinebookstore.model.User;
import com.app.onlinebookstore.repository.RoleRepository;
import com.app.onlinebookstore.repository.ShoppingCartRepository;
import com.app.onlinebookstore.repository.UserRepository;
import com.app.onlinebookstore.service.UserService;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public UserRegistrationResponseDto register(
            UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.findByEmail(
                request.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration.");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setShippingAddress(request.getShippingAddress());

        Set<Role> roles = new HashSet<>();
        Role defoultRole = roleRepository.findByRoleName(
                RoleName.ROLE_USER).orElseThrow(() ->
                new NoSuchElementException("Can't find such role: " + RoleName.ROLE_USER));
        roles.add(defoultRole);
        user.setRoles(roles);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
        User saveUser = userRepository.save(user);
        return userMapper.toUserResponse(saveUser);
    }
}
