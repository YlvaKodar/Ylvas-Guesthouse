package org.spring.theguesthouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.spring.theguesthouse.dto.CustomerDto;
import org.spring.theguesthouse.entity.Booking;
import org.spring.theguesthouse.dto.BookingDTO;
import org.spring.theguesthouse.dto.DetailedBookingDTO;
import org.spring.theguesthouse.repository.BookingRepo;
import org.spring.theguesthouse.repository.CustomerRepo;
import org.spring.theguesthouse.repository.RoomRepo;
import org.spring.theguesthouse.service.BookingService;
import org.spring.theguesthouse.service.CustomerService;
import org.spring.theguesthouse.service.RoomService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final RoomService roomService;
    private final CustomerService customerService;

    private final BookingRepo bookingRepo;
    private final CustomerRepo customerRepo;
    private final RoomRepo roomRepo;

    @Override
    public BookingDTO bookingToDto(Booking booking) {
        return BookingDTO.builder().id(booking.getId()).start_date(booking.getStart_date()).end_date(booking.getEnd_date()).build();
    }

    @Override
    public DetailedBookingDTO bookingToDetailedDto(Booking booking) {
        return DetailedBookingDTO.builder().id(booking.getId())
                .start_date(booking.getStart_date()).end_date(booking.getEnd_date())
                .customer(new CustomerDto(booking.getCustomer().getId(), booking.getCustomer().getName()))
                .rooms(booking.getRooms().stream().map(roomService::roomToDto).collect(Collectors.toList()))
                .build();
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
