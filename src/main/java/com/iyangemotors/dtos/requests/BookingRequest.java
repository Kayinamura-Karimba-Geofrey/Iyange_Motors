package com.iyangemotors.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class BookingRequest {
    @NotNull
    private UUID vehicleId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
