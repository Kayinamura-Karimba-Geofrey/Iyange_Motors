package com.iyangemotors.services.implementations;

import com.iyangemotors.dtos.requests.BookingRequest;
import com.iyangemotors.enums.EBookingStatus;
import com.iyangemotors.exceptions.BadRequestException;
import com.iyangemotors.exceptions.ResourceNotFoundException;
import com.iyangemotors.models.Booking;
import com.iyangemotors.models.User;
import com.iyangemotors.models.Vehicle;
import com.iyangemotors.repositories.BookingRepository;
import com.iyangemotors.repositories.UserRepository;
import com.iyangemotors.repositories.VehicleRepository;
import com.iyangemotors.services.interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Booking createBooking(UUID userId, BookingRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        if (!vehicle.getAvailability()) {
            throw new BadRequestException("Vehicle is currently not available for booking");
        }

        // Check for overlapping bookings
        List<Booking> overlapping = bookingRepository.findOverlappingBookings(
                request.getVehicleId(), request.getStartDate(), request.getEndDate());
        
        if (!overlapping.isEmpty()) {
            throw new BadRequestException("Vehicle is already booked for the selected dates");
        }

        long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());
        if (days <= 0) days = 1; // Minimum 1 day

        Double totalPrice = days * vehicle.getPricePerDay();

        Booking booking = Booking.builder()
                .user(user)
                .vehicle(vehicle)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .totalPrice(totalPrice)
                .status(EBookingStatus.PENDING)
                .build();

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUser(UUID userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    @Transactional
    public Booking approveBooking(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        
        if (booking.getStatus() != EBookingStatus.PENDING) {
            throw new BadRequestException("Only PENDING bookings can be approved");
        }

        booking.setStatus(EBookingStatus.APPROVED);
        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public Booking cancelBooking(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        
        booking.setStatus(EBookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }
}
