package org.spring.theguesthouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.spring.theguesthouse.entity.Booking;
import org.spring.theguesthouse.entity.Customer;
import org.spring.theguesthouse.entity.Room;
import org.spring.theguesthouse.dto.BookingDTO;
import org.spring.theguesthouse.dto.DetailedBookingDTO;
import org.spring.theguesthouse.entity.Booking;
import org.spring.theguesthouse.repository.BookingRepo;
import org.spring.theguesthouse.repository.CustomerRepo;
import org.spring.theguesthouse.repository.RoomRepo;
import org.spring.theguesthouse.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final CustomerRepo customerRepo;
    private final RoomRepo roomRepo;


    @Override
    public BookingDTO bookingToDto(Booking booking) {
        return BookingDTO.builder().id(booking.getId()).start_date(booking.getStart_date()).end_date(booking.getEnd_date()).build();
    }

    @Override
    public DetailedBookingDTO bookingToDetailedDto(Booking booking) {
        return null;
    }

    @Override
    public Booking bookingDtoToBooking(DetailedBookingDTO dto) {
        return null;
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
