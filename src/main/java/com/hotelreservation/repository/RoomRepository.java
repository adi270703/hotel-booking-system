package com.hotelreservation.repository;

import com.hotelreservation.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomNumber(String roomNumber);

    @Query("SELECT r FROM Room r WHERE r.isAvailable = true ORDER BY r.pricePerNight ASC")
    List<Room> findAllAvailableRooms();

    @Query("SELECT r FROM Room r WHERE r.roomType = :roomType AND r.isAvailable = true")
    List<Room> findAvailableRoomsByType(@Param("roomType") String roomType);

    @Query("SELECT r FROM Room r WHERE r.capacity >= :capacity AND r.isAvailable = true")
    List<Room> findAvailableRoomsByCapacity(@Param("capacity") Integer capacity);
}