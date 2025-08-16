package org.spring.theguesthouse;

import org.spring.theguesthouse.entity.Booking;
import org.spring.theguesthouse.entity.Customer;
import org.spring.theguesthouse.entity.Room;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class TheGuesthouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheGuesthouseApplication.class, args);
    }

     @Bean
    public CommandLineRunner commandLineRunner(BookingRepo bookingRepo, CustomerRepo customerRepo, RoomRepo roomRepo) {
        return args -> {

            Room r1 = Room.builder().roomNumber(101).maxGuests(1).build();
            Room r2 = Room.builder().roomNumber(102).maxGuests(2).build();
            Room r3 = Room.builder().roomNumber(103).maxGuests(3).build();
            Room r4 = Room.builder().roomNumber(201).maxGuests(1).build();
            Room r5 = Room.builder().roomNumber(202).maxGuests(4).build();
            Room r6 = Room.builder().roomNumber(203).maxGuests(3).build();
            Room r7 = Room.builder().roomNumber(301).maxGuests(4).build();
            Room r8 = Room.builder().roomNumber(302).maxGuests(2).build();
            Room r9 = Room.builder().roomNumber(303).maxGuests(1).build();
            Room r10 = Room.builder().roomNumber(1337).maxGuests(1).build();

            roomRepo.save(r1);
            roomRepo.save(r2);
            roomRepo.save(r3);
            roomRepo.save(r4);
            roomRepo.save(r5);
            roomRepo.save(r6);
            roomRepo.save(r7);
            roomRepo.save(r8);
            roomRepo.save(r9);
            roomRepo.save(r10);

            Customer c1 = Customer.builder().name("Maja Gräddnos").email("maja@asgrand.ua").build();
            Customer c2 = Customer.builder().name("Gammel-Maja").email("maja@domkyrkotornet.ua").build();
            Customer c3 = Customer.builder().name("Gullan von Arkadien").email("gullan@arkadien.ua").build();
            Customer c4 = Customer.builder().name("Laban i Observatorielunden").email("laban@observatorielunden.ua").build();
            Customer c5 = Customer.builder().name("Sitting Bill").email("bill@amirikatt.us").build();

            customerRepo.save(c1);
            customerRepo.save(c2);
            customerRepo.save(c3);
            customerRepo.save(c4);
            customerRepo.save(c5);

            Booking b1 = Booking.builder().customer(c1)
                    .startDate(LocalDate.of(2025, 10, 15))
                    .endDate(LocalDate.of(2025, 10, 18)).numberOfGuests(1).room(r1).build();

            Booking b2 = Booking.builder().customer(c1)
                    .startDate(LocalDate.of(2025, 10, 2))
                    .endDate(LocalDate.of(2025, 10, 3)).numberOfGuests(2).room(r2).build();

            Booking b3 = Booking.builder().customer(c3)
                    .startDate(LocalDate.of(2025, 8, 15))
                    .endDate(LocalDate.of(2025, 9, 1)).numberOfGuests(4).room(r5).build();

            Booking b4 = Booking.builder().customer(c2)
                    .startDate(LocalDate.of(2025, 8, 16))
                    .endDate(LocalDate.of(2025, 8, 18)).numberOfGuests(2).room(r8).build();


            bookingRepo.save(b1);
            bookingRepo.save(b2);
            bookingRepo.save(b3);
            bookingRepo.save(b4);

        };

    }
}
