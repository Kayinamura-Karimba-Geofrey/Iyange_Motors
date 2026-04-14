package com.iyangemotors.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VehicleRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String type;

    @NotNull
    @Positive
    private Double pricePerDay;

    private String description;

    private List<String> imageUrls;
}
