package org.spring.theguesthouse.controller;

import lombok.RequiredArgsConstructor;
import org.spring.theguesthouse.dto.RoomDto;
import org.spring.theguesthouse.entity.Room;
import org.spring.theguesthouse.repository.RoomRepo;
import org.spring.theguesthouse.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final RoomRepo roomRepo; //Tillfällig: all kommunikation med databasen ska senare gå via Service

    //localhost:8080/guesthouse/rooms
    @RequestMapping("guesthouse/rooms")
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    //TODO: Flytta logik nedanför till Service
    //Allt nedanför är tillfälliga metoder: Om vi vill ha kvar dem ska logik flyttas till Service.
    //localhost:8080/guesthouse/rooms/{id}
    @RequestMapping("guesthouse/rooms/{id}")
    public RoomDto getRoomById(@PathVariable long id) {
        return roomService.getAllRooms().stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    //localhost:8080/guesthouse/rooms/delete/{id}
    @RequestMapping("guesthouse/rooms/delete/{id}")
    public String deleteRoomById(@PathVariable long id) {
        roomRepo.deleteById(id);
        return "Room with id " + id + " deleted";
    }

    //localhost:8080/guesthouse/rooms/add?roomNr=
    @RequestMapping("guesthouse/rooms/add")
    public String addRoom(@RequestParam int roomNr) {
        roomRepo.save(Room.builder().roomNumber(roomNr).build());
        return "Room with number " + roomNr + " added";
    }
}
