package com.iyangemotors.dtos.auth;

import com.iyangemotors.enums.ERole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    private String fullName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String driverLicense;

    private ERole role; // Optional, default is CUSTOMER
}
