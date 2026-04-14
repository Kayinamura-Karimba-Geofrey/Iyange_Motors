package com.iyangemotors.services.implementations;

import com.iyangemotors.dtos.requests.VehicleRequest;
import com.iyangemotors.exceptions.ResourceNotFoundException;
import com.iyangemotors.models.Vehicle;
import com.iyangemotors.models.VehicleImage;
import com.iyangemotors.repositories.VehicleImageRepository;
import com.iyangemotors.repositories.VehicleRepository;
import com.iyangemotors.services.interfaces.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleImageRepository vehicleImageRepository;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findByAvailabilityTrue();
    }

    @Override
    public Vehicle getVehicleById(UUID id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
    }

    @Override
    @Transactional
    public Vehicle createVehicle(VehicleRequest request) {
        Vehicle vehicle = Vehicle.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .model(request.getModel())
                .type(request.getType())
                .pricePerDay(request.getPricePerDay())
                .description(request.getDescription())
                .availability(true)
                .build();

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        if (request.getImageUrls() != null) {
            List<VehicleImage> images = request.getImageUrls().stream()
                    .map(url -> VehicleImage.builder().imageUrl(url).vehicle(savedVehicle).build())
                    .collect(Collectors.toList());
            vehicleImageRepository.saveAll(images);
            savedVehicle.setImages(images);
        }

        return savedVehicle;
    }

    @Override
    @Transactional
    public Vehicle updateVehicle(UUID id, VehicleRequest request) {
        Vehicle vehicle = getVehicleById(id);

        vehicle.setName(request.getName());
        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setType(request.getType());
        vehicle.setPricePerDay(request.getPricePerDay());
        vehicle.setDescription(request.getDescription());

        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicle(UUID id) {
        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);
    }
}
