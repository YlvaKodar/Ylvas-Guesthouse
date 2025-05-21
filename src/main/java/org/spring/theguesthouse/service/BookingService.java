package org.spring.theguesthouse.service;

import org.spring.theguesthouse.dto.BookingDTO;
import org.spring.theguesthouse.entity.Booking;
import org.spring.theguesthouse.dto.DetailedBookingDTO;

import java.util.List;

public interface BookingService {
    // Convert entities to DTOs
    BookingDTO bookingToDto(Booking booking);
    DetailedBookingDTO bookingToDetailedDto(Booking booking);

    // Convert DTOs to entities
    Booking bookingDtoToBooking(DetailedBookingDTO dto);

    // Get operations
    List<DetailedBookingDTO> getAllBookings();
    // DetailedBookingDTO getBookingById(Long id);
    // List<DetailedBookingDTO> getBookingsByCustomerId(Long customerId);
    // List<DetailedBookingDTO> getBookingsByRoomId(Long roomId);

    // CRUD operations
    // BookingDTO createBooking(BookingDTO bookingDTO);
    // BookingDTO updateBooking(Long id, BookingDTO bookingDTO);
    // void deleteBooking(Long id);

    // Business logic
    // boolean isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate, Long excludeBookingId);
}
