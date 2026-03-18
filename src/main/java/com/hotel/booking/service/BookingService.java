package com.hotel.booking.service;

import com.hotel.booking.entity.Booking;
import com.hotel.booking.entity.Room;
import com.hotel.booking.repository.BookingRepository;
import com.hotel.booking.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService 
{

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public BookingService(RoomRepository roomRepository, BookingRepository bookingRepository)
    {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public Booking createBooking(Booking bookingRequest, Long roomId) 
    {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room ID " + roomId + " not found."));
        
        if (!room.isAvailable()) 
        {
            throw new RuntimeException("Sorry, Room " + room.getRoomNumber() + " is already booked.");
        }
        
        room.setAvailable(false);
        roomRepository.save(room);

        bookingRequest.setRoom(room);
        return bookingRepository.save(bookingRequest);
    }
}