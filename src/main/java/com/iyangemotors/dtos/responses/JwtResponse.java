package com.iyangemotors.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private UUID id;
    private String fullName;
    private String email;
    private String role;

    public JwtResponse(String accessToken, UUID id, String fullName, String email, String role) {
        this.token = accessToken;
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }
}
