package com.iyangemotors.services.interfaces;

import com.iyangemotors.dtos.auth.LoginRequest;
import com.iyangemotors.dtos.auth.RegisterRequest;
import com.iyangemotors.dtos.responses.JwtResponse;
import com.iyangemotors.dtos.responses.MessageResponse;

public interface AuthService {
    MessageResponse registerUser(RegisterRequest registerRequest);
    JwtResponse authenticateUser(LoginRequest loginRequest);
}
