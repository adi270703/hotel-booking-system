package com.hotelreservation.service;

import com.hotelreservation.dto.BookingDTO;
import com.hotelreservation.entity.Booking;
import com.hotelreservation.entity.Room;
import com.hotelreservation.exception.BookingNotFoundException;
import com.hotelreservation.exception.DateOverlapException;
import com.hotelreservation.exception.RoomNotFoundException;
import com.hotelreservation.repository.BookingRepository;
import com.hotelreservation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        // Validate dates
        if (bookingDTO.getCheckInDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date cannot be in the past");
        }
        if (bookingDTO.getCheckOutDate().isBefore(bookingDTO.getCheckInDate())) {
            throw new IllegalArgumentException("Check-out date must be after check-in date");
        }

        // Get room
        Room room = roomRepository.findById(bookingDTO.getRoomId())
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + bookingDTO.getRoomId() + " not found"));

        // Check for date overlap
        int overlappingBookings = bookingRepository.countOverlappingBookings(
                room.getRoomId(),
                bookingDTO.getCheckInDate(),
                bookingDTO.getCheckOutDate()
        );

        if (overlappingBookings > 0) {
            throw new DateOverlapException("Room is already booked for the selected dates. Please choose different dates.");
        }

        // Calculate total price
        long nights = ChronoUnit.DAYS.between(bookingDTO.getCheckInDate(), bookingDTO.getCheckOutDate());
        Double totalPrice = nights * room.getPricePerNight();

        // Create booking
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setGuestName(bookingDTO.getGuestName());
        booking.setGuestEmail(bookingDTO.getGuestEmail());
        booking.setGuestPhone(bookingDTO.getGuestPhone());
        booking.setCheckInDate(bookingDTO.getCheckInDate());
        booking.setCheckOutDate(bookingDTO.getCheckOutDate());
        booking.setTotalPrice(totalPrice);
        booking.setBookingStatus("CONFIRMED");
        booking.setSpecialRequests(bookingDTO.getSpecialRequests());
        booking.setCreatedAt(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);
        return convertToDTO(savedBooking);
    }

    public BookingDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID " + bookingId + " not found"));
        return convertToDTO(booking);
    }

    public List<BookingDTO> getBookingsByGuestEmail(String guestEmail) {
        List<Booking> bookings = bookingRepository.findByGuestEmail(guestEmail);
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsByRoomId(Long roomId) {
        List<Booking> bookings = bookingRepository.findByRoomId(roomId);
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<BookingDTO> getAllConfirmedBookings() {
        List<Booking> bookings = bookingRepository.findAllConfirmedBookings();
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public BookingDTO cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID " + bookingId + " not found"));

        booking.setBookingStatus("CANCELLED");
        booking.setUpdatedAt(LocalDateTime.now());
        Booking updatedBooking = bookingRepository.save(booking);
        return convertToDTO(updatedBooking);
    }

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setRoomId(booking.getRoom().getRoomId());
        dto.setGuestName(booking.getGuestName());
        dto.setGuestEmail(booking.getGuestEmail());
        dto.setGuestPhone(booking.getGuestPhone());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setNumberOfNights(ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate()));
        dto.setTotalPrice(booking.getTotalPrice());
        dto.setBookingStatus(booking.getBookingStatus());
        dto.setSpecialRequests(booking.getSpecialRequests());
        dto.setCreatedAt(booking.getCreatedAt());
        dto.setUpdatedAt(booking.getUpdatedAt());
        return dto;
    }
}