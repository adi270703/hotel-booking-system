package com.hotel.booking.service;

import com.hotel.booking.entity.Room;
import com.hotel.booking.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService 
{

    private final RoomRepository roomRepository;
    public RoomService(RoomRepository roomRepository) 
    {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllAvailableRooms() 
    {
        return roomRepository.findAll()
                .stream()
                .filter(Room::isAvailable)
                .collect(Collectors.toList());
    }
    
    public Room addRoom(Room room) 
    {
        return roomRepository.save(room);
    }
}