package com.iyangemotors.services.interfaces;

import com.iyangemotors.dtos.requests.VehicleRequest;
import com.iyangemotors.models.Vehicle;

import java.util.List;
import java.util.UUID;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAvailableVehicles();
    Vehicle getVehicleById(UUID id);
    Vehicle createVehicle(VehicleRequest vehicleRequest);
    Vehicle updateVehicle(UUID id, VehicleRequest vehicleRequest);
    void deleteVehicle(UUID id);
}
