package com.accommodation.accommodation.controllers;


import com.accommodation.accommodation.controllers.dto.AccommodationUpdateDTO;
import com.accommodation.accommodation.controllers.dto.LocationDTO;
import com.accommodation.accommodation.model.Location;
import com.accommodation.accommodation.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accommodations")
public class LocationController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private LocationService service;

    @PostMapping
    public ResponseEntity<Location> createAccommodation(@Valid @RequestBody LocationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerAccommodation(objectMapper.convertValue(dto, Location.class)));
    }

    @GetMapping
    public ResponseEntity<List<Location>> createAccommodation() {
        return ResponseEntity.ok().body(service.getAllAccommodations());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Location> updateAccommodation(@PathVariable Long id, @RequestBody AccommodationUpdateDTO dto) {
        return ResponseEntity.ok().body(service.updateAccommodation(dto, id));
    }

    @DeleteMapping("/{idAccommodation}")
    public ResponseEntity<Void> updateAmenitie(@PathVariable Long idAccommodation) {
        service.deleteAccommodation(idAccommodation);
        return ResponseEntity.noContent().build();
    }

}
