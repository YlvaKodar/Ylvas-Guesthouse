package org.spring.theguesthouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.spring.theguesthouse.dto.*;
import org.spring.theguesthouse.entity.Booking;
import org.spring.theguesthouse.entity.Customer;
import org.spring.theguesthouse.entity.Room;
import org.spring.theguesthouse.repository.BookingRepo;
import org.spring.theguesthouse.repository.CustomerRepo;
import org.spring.theguesthouse.repository.RoomRepo;
import org.spring.theguesthouse.service.BookingService;
import org.spring.theguesthouse.service.RoomService;
import org.spring.theguesthouse.service.CustomerService;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final RoomService roomService;
    private final BookingRepo bookingRepo;
    private final CustomerRepo customerRepo;
    private final RoomRepo roomRepo;

    @Override
    public BookingDTO bookingToDto(Booking booking) {
        return BookingDTO.builder().id(booking.getId()).startDate(booking.getStartDate()).endDate(booking.getEndDate()).build();
    }

    @Override
    public DetailedBookingDTO bookingToDetailedDto(Booking booking) {
        return DetailedBookingDTO.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .numberOfGuests(booking.getNumberOfGuests())
                .customer(new CustomerDto(booking.getCustomer().getId(), booking.getCustomer().getName()))
                .room(RoomDto.builder()
                        .id(booking.getRoom().getId())
                        .roomNumber(booking.getRoom().getRoomNumber())
                        .maxGuests(booking.getRoom().getMaxGuests())
                        .build())
                .build();
    }

    @Override
    public Booking detailedBookingDtoToBooking(DetailedBookingDTO dto) {
        // Return null if the DTO is null
        if (dto == null) {
            return null;
        }

        //Get customer or throw exception
        Customer customer = customerRepo.findById(dto.getCustomer().getId()).
                orElseThrow(() -> new RuntimeException("Cannot make booking; customer reference is missing"));

        //Get room or throw exception
        Room room = roomRepo.findById(dto.getRoom().getId()).
                orElseThrow(() -> new RuntimeException("Cannot make booking; room reference is missing"));

        //Create new Booking using builder pattern
        return Booking.builder()
                .id(dto.getId())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .numberOfGuests(dto.getNumberOfGuests())
                .customer(customer)
                .room(room)
                .build();
    }

    @Override
    public DetailedBookingDTO getBookingById(long id) {
        return bookingRepo.findById(id).map(this::bookingToDetailedDto).orElse(null);
    }

    @Override
    public String addBooking(DetailedBookingDTO booking) {
        bookingRepo.save(detailedBookingDtoToBooking(booking));
        return "Booking successfully added";
    }

    @Override
    public DetailedBookingDTO updateBooking(DetailedBookingDTO booking) {
        Booking existingBooking = bookingRepo.findById(booking.getId()).orElseThrow(() -> new RuntimeException("Booking with id" + booking.getId() + " not found"));

        if(!(checkDates(booking.getStartDate()) || checkDateOrder(booking.getStartDate(), booking.getEndDate()))) {
            throw new RuntimeException("Booking dates are wrong.");
        }

        existingBooking.setStartDate(booking.getStartDate());
        existingBooking.setEndDate(booking.getEndDate());
        existingBooking.setNumberOfGuests(booking.getNumberOfGuests());
        existingBooking.setRoom(roomRepo.findById(booking.getRoom().getId()).orElseThrow(() -> new RuntimeException("Room reference is missing")));

        bookingRepo.save(existingBooking);
        return booking;
    }

    @Override
    public List<BookingDTO> getAllBookingDtos() {
        return bookingRepo.findAll().stream().map(this::bookingToDto).toList();
    }

    @Override
    public List<DetailedBookingDTO> getAllDetailedBookingDtos() {
        return bookingRepo.findAll().stream().map(this::bookingToDetailedDto).toList();
    }

    @Override
    public DeleteResponseDto deleteBooking(Long bookingId) {
        return bookingRepo.findById(bookingId).
                map(booking -> { bookingRepo.deleteById(bookingId);
                    return new DeleteResponseDto(true, "Booking has been deleted.");})
                .orElse(new DeleteResponseDto(false, "Booking does not exists"));

    }

    @Override
    public Boolean checkDateOrder(LocalDate start, LocalDate end) {
        return end.isAfter(start);
    }

    @Override
    public Boolean checkDates(LocalDate startDate) {
        return startDate.isAfter(LocalDate.now());
    }

}
