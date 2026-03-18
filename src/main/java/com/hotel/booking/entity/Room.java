package com.hotel.booking.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
public class Room 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number", unique = true, nullable = false)
    private String roomNumber;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "price_per_night")
    private BigDecimal pricePerNight;

    @Column(name = "is_available")
    private boolean isAvailable = true;

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getRoomNumber() 
	{
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) 
	{
		this.roomNumber = roomNumber;
	}

	public String getRoomType() 
	{
		return roomType;
	}

	public void setRoomType(String roomType) 
	{
		this.roomType = roomType;
	}

	public BigDecimal getPricePerNight() 
	{
		return pricePerNight;
	}

	public void setPricePerNight(BigDecimal pricePerNight) 
	{
		this.pricePerNight = pricePerNight;
	}

	public boolean isAvailable() 
	{
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) 
	{
		this.isAvailable = isAvailable;
	}
    
    

   
}