package org.spring.theguesthouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.spring.theguesthouse.dto.CustomerDto;
import org.spring.theguesthouse.dto.RoomDto;
import org.spring.theguesthouse.entity.Booking;
import org.spring.theguesthouse.entity.Customer;
import org.spring.theguesthouse.entity.Room;
import org.spring.theguesthouse.dto.BookingDTO;
import org.spring.theguesthouse.dto.DetailedBookingDTO;
import org.spring.theguesthouse.repository.BookingRepo;
import org.spring.theguesthouse.repository.CustomerRepo;
import org.spring.theguesthouse.repository.RoomRepo;
import org.spring.theguesthouse.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final CustomerRepo customerRepo;
    private final RoomRepo roomRepo;


    @Override
    public BookingDTO bookingToDto(Booking booking) {
        return BookingDTO.builder().id(booking.getId()).startDate(booking.getStartDate()).endDate(booking.getEndDate()).build();
    }

    @Override
    public DetailedBookingDTO bookingToDetailedDto(Booking booking) {
        return null;
    }

    @Override
    public Booking bookingDtoToBooking(DetailedBookingDTO dto) {
        // Return null if the DTO is null
        if (dto == null) {
            return null;
        }
        // Create new Booking using builder pattern
        return Booking.builder()
                .id(dto.getId())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();

    }

    @Override
    public String addBooking(DetailedBookingDTO booking) {
        // Extract room IDs from the booking
        List<Long> roomIds = new ArrayList<>();
        for (RoomDto room : booking.getRooms()) {
            roomIds.add(room.getId());
        }

        // Check availability before saving
        if (!roomsAvailable(roomIds, booking.getStartDate(), booking.getEndDate())) {
            return "Booking failed, room is not available";
        }
        bookingRepo.save(bookingDtoToBooking(booking));
        return "Booking successfully added";
    }

    public boolean roomsAvailable(List<Long> roomIds, Date startDate, Date endDate) {

        if (roomIds == null || startDate == null || endDate == null) {
            return false;
        }

        List<Booking> allBookings = bookingRepo.findAll();

        for (Booking existingBooking : allBookings) {
            // Check if dates overlap
            if (startDate.before(existingBooking.getEndDate()) && endDate.after(existingBooking.getStartDate())) {

                // Check if any requested room is already booked
                for (Long roomId : roomIds) {
                    for (Room room : existingBooking.getRooms()) {
                        if (room.getId().equals(roomId)) {
                            return false; // Room is unavailable
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public List<BookingDTO> getAllBookingDtos() {
        return bookingRepo.findAll().stream().map(this::bookingToDto).toList();
    }

    @Override
    public List<DetailedBookingDTO> getAllDetailedBookingDtos() {
        return bookingRepo.findAll().stream().map(this::bookingToDetailedDto).toList();
    }
}
