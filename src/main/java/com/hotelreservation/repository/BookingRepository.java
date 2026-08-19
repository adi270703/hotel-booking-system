package com.hotelreservation.repository;

import com.hotelreservation.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.guestEmail = :email AND b.bookingStatus = 'CONFIRMED'")
    List<Booking> findByGuestEmail(@Param("email") String email);

    @Query("SELECT b FROM Booking b WHERE b.room.roomId = :roomId AND b.bookingStatus = 'CONFIRMED'")
    List<Booking> findByRoomId(@Param("roomId") Long roomId);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.room.roomId = :roomId " +
           "AND b.bookingStatus = 'CONFIRMED' " +
           "AND b.checkInDate < :checkOutDate AND b.checkOutDate > :checkInDate")
    int countOverlappingBookings(@Param("roomId") Long roomId,
                                 @Param("checkInDate") LocalDate checkInDate,
                                 @Param("checkOutDate") LocalDate checkOutDate);

    @Query("SELECT b FROM Booking b WHERE b.bookingStatus = 'CONFIRMED' ORDER BY b.checkInDate DESC")
    List<Booking> findAllConfirmedBookings();
}