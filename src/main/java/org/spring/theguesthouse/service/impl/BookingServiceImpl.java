package org.spring.theguesthouse.service.impl;

import org.spring.theguesthouse.entity.Booking;
import org.spring.theguesthouse.entity.Customer;
import org.spring.theguesthouse.entity.Room;
import org.spring.theguesthouse.dto.BookingDTO;
import org.spring.theguesthouse.dto.DetailedBookingDTO;
import org.spring.theguesthouse.entity.Booking;
import org.spring.theguesthouse.repository.BookingRepo;
import org.spring.theguesthouse.repository.CustomerRepo;
import org.spring.theguesthouse.repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Override
    public BookingDTO bookingDTO(Booking booking) {}

    @Override
    public DetailedBookingDTO bookingToDetailedDto(Booking booking) {}

    @Override
    public Booking bookingDtoToBooking(BookingDTO dto) {}

    @Override
    public List<DetailedBookingDTO> getAllBookings() {}
}
