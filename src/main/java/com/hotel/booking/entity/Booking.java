package com.hotel.booking.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "guest_name", nullable = false)
    private String guestName;

    @Column(name = "guest_email", nullable = false)
    private String guestEmail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getGuestName() 
	{
		return guestName;
	}

	public void setGuestName(String guestName) 
	{
		this.guestName = guestName;
	}

	public String getGuestEmail() 
	{
		return guestEmail;
	}

	public void setGuestEmail(String guestEmail) 
	{
		this.guestEmail = guestEmail;
	}

	public Room getRoom() 
	{
		return room;
	}

	public void setRoom(Room room) 
	{
		this.room = room;
	}

	public LocalDate getCheckInDate() 
	{
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) 
	{
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() 
	{
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) 
	{
		this.checkOutDate = checkOutDate;
	}

    
}