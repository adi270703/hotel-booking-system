package com.hotel.booking.controller;

import com.hotel.booking.entity.Room;
import com.hotel.booking.service.RoomService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController 
{

    private final RoomService roomService;

    public RoomController(RoomService roomService) 
    {
        this.roomService = roomService;
    }
    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms() 
    {
        List<Room> availableRooms = roomService.getAllAvailableRooms();
        return ResponseEntity.ok(availableRooms);
    }
    
    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) 
    {
        Room savedRoom = roomService.addRoom(room);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }
}