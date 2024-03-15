package com.accommodation.accommodation.controllers;

import com.accommodation.accommodation.controllers.dto.RoomUpdateDTO;
import com.accommodation.accommodation.controllers.dto.RoomsDTO;
import com.accommodation.accommodation.model.Room;
import com.accommodation.accommodation.model.Rooms;
import com.accommodation.accommodation.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RoomService service;

    @PostMapping("/{idBuilding}")
    public ResponseEntity<List<Room>> registerRooms(@Valid @RequestBody RoomsDTO dto, @PathVariable Long idBuilding) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerRooms(objectMapper.convertValue(dto, Rooms.class).getRooms(), idBuilding));
    }

    @GetMapping("/{idBuilding}")
    public ResponseEntity<List<Room>> registerRooms(@PathVariable Long idBuilding) {
        return ResponseEntity.ok().body(service.getAllRoomsByBuilding(idBuilding));
    }


    @PatchMapping("/{idRoom}")
    public ResponseEntity<Room> registerRooms(@Valid @RequestBody RoomUpdateDTO dto, @PathVariable Long idBuilding) {
        return ResponseEntity.ok().body(service.updateRoom(dto, idBuilding));
    }

}
