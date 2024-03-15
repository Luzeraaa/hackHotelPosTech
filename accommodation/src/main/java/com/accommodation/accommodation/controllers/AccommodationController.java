package com.accommodation.accommodation.controllers;


import com.accommodation.accommodation.controllers.dto.AccommodationUpdateDTO;
import com.accommodation.accommodation.controllers.dto.AccomodationDTO;
import com.accommodation.accommodation.model.Accommodation;
import com.accommodation.accommodation.service.AccommodationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accommodations")
public class AccommodationController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AccommodationService service;

    @PostMapping
    public ResponseEntity<Accommodation> createAccommodation(@Valid @RequestBody AccomodationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerAccommodation(objectMapper.convertValue(dto, Accommodation.class)));
    }

    @GetMapping
    public ResponseEntity<List<Accommodation>> createAccommodation() {
        return ResponseEntity.ok().body(service.getAllAccommodations());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Accommodation> updateAccommodation(@PathVariable Long id, @RequestBody AccommodationUpdateDTO dto) {
        return ResponseEntity.ok().body(service.updateAccommodation(dto, id));
    }

}
