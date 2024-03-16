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
@RequestMapping("/locations")
public class LocationController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private LocationService service;

    @PostMapping
    public ResponseEntity<Location> createLocation(@Valid @RequestBody LocationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerLocation(objectMapper.convertValue(dto, Location.class)));
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocation() {
        return ResponseEntity.ok().body(service.getAllLocation());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody AccommodationUpdateDTO dto) {
        return ResponseEntity.ok().body(service.updateLocation(dto, id));
    }

    @DeleteMapping("/{idAccommodation}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long idAccommodation) {
        service.deleteLocation(idAccommodation);
        return ResponseEntity.noContent().build();
    }

}
