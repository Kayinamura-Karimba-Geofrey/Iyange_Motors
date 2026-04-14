package com.iyangemotors.services.interfaces;

import com.iyangemotors.dtos.requests.BookingRequest;
import com.iyangemotors.models.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    Booking createBooking(UUID userId, BookingRequest request);
    List<Booking> getBookingsByUser(UUID userId);
    List<Booking> getAllBookings();
    Booking approveBooking(UUID bookingId);
    Booking cancelBooking(UUID bookingId);
}
