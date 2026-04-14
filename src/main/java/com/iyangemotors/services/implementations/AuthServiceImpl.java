package com.iyangemotors.services.implementations;

import com.iyangemotors.dtos.auth.LoginRequest;
import com.iyangemotors.dtos.auth.RegisterRequest;
import com.iyangemotors.dtos.responses.JwtResponse;
import com.iyangemotors.dtos.responses.MessageResponse;
import com.iyangemotors.enums.ERole;
import com.iyangemotors.exceptions.BadRequestException;
import com.iyangemotors.models.User;
import com.iyangemotors.repositories.UserRepository;
import com.iyangemotors.security.JwtUtils;
import com.iyangemotors.security.UserDetailsImpl;
import com.iyangemotors.services.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public MessageResponse registerUser(RegisterRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Error: Email is already in use!");
        }

        // Create new user's account
        User user = User.builder()
                .fullName(signUpRequest.getFullName())
                .email(signUpRequest.getEmail())
                .phone(signUpRequest.getPhone())
                .password(encoder.encode(signUpRequest.getPassword()))
                .driverLicense(signUpRequest.getDriverLicense())
                .role(signUpRequest.getRole() != null ? signUpRequest.getRole() : ERole.CUSTOMER)
                .build();

        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getFullName(),
                userDetails.getEmail(),
                role);
    }
}
