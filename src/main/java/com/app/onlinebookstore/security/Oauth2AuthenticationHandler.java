package com.app.onlinebookstore.security;

import com.app.onlinebookstore.exaption.EntityNotFoundException;
import com.app.onlinebookstore.model.Role;
import com.app.onlinebookstore.model.RoleName;
import com.app.onlinebookstore.model.ShoppingCart;
import com.app.onlinebookstore.model.User;
import com.app.onlinebookstore.repository.RoleRepository;
import com.app.onlinebookstore.repository.ShoppingCartRepository;
import com.app.onlinebookstore.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class Oauth2AuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final String URI_TOKEN = "http://localhost:4200/login?token=";
    private static final String EMAIL = "email";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute(EMAIL);
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            User gitHubUser = new User();
            gitHubUser.setEmail(email);
            gitHubUser.setFirstName(oauth2User.getAttribute("login"));
            gitHubUser.setLastName(oauth2User.getAttribute("name"));
            gitHubUser.setPassword(passwordEncoder.encode("password"));

            Set<Role> roles = new HashSet<>();
            Role defoultRole = roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(
                    () -> new EntityNotFoundException("Current role does not exist."));
            roles.add(defoultRole);
            gitHubUser.setRoles(roles);

            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(gitHubUser);
            shoppingCartRepository.save(shoppingCart);

            userRepository.save(gitHubUser);
        }
        String token = jwtUtil.generateToken(email);
        response.sendRedirect(URI_TOKEN + token);
    }
}
