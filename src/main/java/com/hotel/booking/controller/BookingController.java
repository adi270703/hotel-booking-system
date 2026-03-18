package com.hotel.booking.controller;

import com.hotel.booking.entity.Booking;
import com.hotel.booking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController 
{

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) 
    {
        this.bookingService = bookingService;
    }
    
    @PostMapping("/room/{roomId}")
    public ResponseEntity<?> bookRoom(@RequestBody Booking bookingRequest, @PathVariable Long roomId) 
    {
        try 
        {
            Booking savedBooking = bookingService.createBooking(bookingRequest, roomId);
            
            return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
        }
        catch (RuntimeException e) 
        {
           
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}