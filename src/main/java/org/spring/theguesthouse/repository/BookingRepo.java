package org.spring.theguesthouse.repository;

import org.spring.theguesthouse.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

    // Find all bookings for a specific customer
    List<Booking> findByCustomerId(Long customerId);

    // Find all bookings for a specific room
    List<Booking> findByRoomId(Long roomId);

    // Check a rooms availability for a given date range
    // Excludes the booking with the specified ID
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Booking b " +
            "WHERE b.room.id = :roomId " +
            "AND b.checkInDate <= :checkOutDate " +
            "AND b.checkOutDate >= :checkInDate " +
            "AND (b.id <> :excludeBookingId OR :excludeBookingId IS NULL)")
    boolean isRoomBooked(@Param("roomId") Long roomId,
                         @Param("checkInDate") LocalDate checkInDate,
                         @Param("checkOutDate") LocalDate checkOutDate,
                         @Param("excludeBookingId") Long excludeBookingId);
}
