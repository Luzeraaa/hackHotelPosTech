package com.accommodation.accommodation.controllers;


import com.accommodation.accommodation.controllers.dto.AccommodationDTO;
import com.accommodation.accommodation.model.Accommodation;
import com.accommodation.accommodation.model.TotalReservation;
import com.accommodation.accommodation.service.AccommodationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accommodations")
public class AccommodationController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AccommodationService service;


    @PostMapping("/{idUser}/{idBuilding}/{idRoom}")
    public ResponseEntity<Accommodation> registerAccommodation(@Valid @RequestBody AccommodationDTO dto,
                                                               @PathVariable Long idUser,
                                                               @PathVariable Long idBuilding,
                                                               @PathVariable Long idRoom) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.registerAccommodation(objectMapper.convertValue(dto, Accommodation.class), idUser, idBuilding, idRoom));
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<List<Accommodation>> getAccommodation(@PathVariable Long idUser) {
        return ResponseEntity.ok().body(service.getAccommodationByUser(idUser));
    }

    @GetMapping("/total-reservation/{idUser}")
    public ResponseEntity<TotalReservation> getTotalReservation(@PathVariable Long idUser) {
        return ResponseEntity.ok().body(service.getTotalReservation(idUser));
    }
}
