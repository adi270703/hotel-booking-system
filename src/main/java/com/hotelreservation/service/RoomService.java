package com.hotelreservation.service;

import com.hotelreservation.dto.RoomDTO;
import com.hotelreservation.entity.Room;
import com.hotelreservation.exception.RoomNotFoundException;
import com.hotelreservation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setCapacity(roomDTO.getCapacity());
        room.setPricePerNight(roomDTO.getPricePerNight());
        room.setDescription(roomDTO.getDescription());
        room.setIsAvailable(true);
        room.setCreatedAt(LocalDateTime.now());

        Room savedRoom = roomRepository.save(room);
        return convertToDTO(savedRoom);
    }

    public RoomDTO getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found"));
        return convertToDTO(room);
    }

    public List<RoomDTO> getAllAvailableRooms() {
        List<Room> availableRooms = roomRepository.findAllAvailableRooms();
        return availableRooms.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<RoomDTO> getRoomsByType(String roomType) {
        List<Room> rooms = roomRepository.findAvailableRoomsByType(roomType);
        return rooms.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<RoomDTO> getRoomsByCapacity(Integer capacity) {
        List<Room> rooms = roomRepository.findAvailableRoomsByCapacity(capacity);
        return rooms.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public RoomDTO updateRoom(Long roomId, RoomDTO roomDTO) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found"));

        if (roomDTO.getRoomNumber() != null) {
            room.setRoomNumber(roomDTO.getRoomNumber());
        }
        if (roomDTO.getRoomType() != null) {
            room.setRoomType(roomDTO.getRoomType());
        }
        if (roomDTO.getCapacity() != null) {
            room.setCapacity(roomDTO.getCapacity());
        }
        if (roomDTO.getPricePerNight() != null) {
            room.setPricePerNight(roomDTO.getPricePerNight());
        }
        if (roomDTO.getDescription() != null) {
            room.setDescription(roomDTO.getDescription());
        }
        if (roomDTO.getIsAvailable() != null) {
            room.setIsAvailable(roomDTO.getIsAvailable());
        }

        room.setUpdatedAt(LocalDateTime.now());
        Room updatedRoom = roomRepository.save(room);
        return convertToDTO(updatedRoom);
    }

    public void deleteRoom(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found"));
        roomRepository.delete(room);
    }

    private RoomDTO convertToDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setRoomId(room.getRoomId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setRoomType(room.getRoomType());
        dto.setCapacity(room.getCapacity());
        dto.setPricePerNight(room.getPricePerNight());
        dto.setIsAvailable(room.getIsAvailable());
        dto.setDescription(room.getDescription());
        dto.setCreatedAt(room.getCreatedAt());
        dto.setUpdatedAt(room.getUpdatedAt());
        return dto;
    }
}