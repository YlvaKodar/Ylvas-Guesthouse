package org.spring.theguesthouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.spring.theguesthouse.dto.CustomerDto;
import org.spring.theguesthouse.dto.RoomDto;
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
import org.spring.theguesthouse.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final RoomService roomService;
    // TODO: När CustomerService finns, avkommentera nedan.
    //  private final CustomerService customerService;

    private final BookingRepo bookingRepo;
    private final CustomerRepo customerRepo;
    private final RoomRepo roomRepo;
    private final CacheManagerCustomizers cacheManagerCustomizers;


    @Override
    public BookingDTO bookingToDto(Booking booking) {
        return BookingDTO.builder().id(booking.getId()).start_date(booking.getStart_date()).end_date(booking.getEnd_date()).build();
    }

    @Override
    public DetailedBookingDTO bookingToDetailedDto(Booking booking) {
        Customer customer = booking.getCustomer();

        //För att inte få dubbletter av customerDto:
        CustomerDto tempCustDto;
        //TODO: In en if-sats: kolla om det finns en custDTO med Customerobjektets id (med typ CustomerService:getAllCustomers().stream().filter()...)
        //TODO: Om det finns, tilldela tempCustDto den. Om den inte finns, tempCustDto = customerService.customerToDto(customer).

        //För att inte få dubbletter av roomDTO:
        List<RoomDto> tempRooms = new ArrayList<>();
        //For each room in Booking.rooms: if there is a matching roomDto: add to tempRooms. If there is not, create one and add to tempRooms.
        booking.getRooms().forEach(room ->{
            RoomDto tempRoom =  roomService.getAllRooms().stream().filter(roomDto -> roomDto.getId().equals(room.getId())).findFirst().orElse(null);
                if(tempRoom == null){
                    tempRoom = roomService.roomToDto(room);
                }
                tempRooms.add(tempRoom);
        });

        return DetailedBookingDTO.builder().id(booking.getId())
                .start_date(booking.getStart_date()).end_date(booking.getEnd_date())
                .rooms(tempRooms)
                //TODO: När customerService har ovanstående metoder, kommentera in nedan.
                //.customer(tempRooms)
                .build();
    }

    @Override
    public Booking bookingDtoToBooking(DetailedBookingDTO dto) {
        return null;
    }

    @Override
    public List<DetailedBookingDTO> getAllBookings() {
        return List.of();
    }
}
