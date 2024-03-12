package com.accommodation.accommodation.controllers;


import com.accommodation.accommodation.controllers.dto.AccomodationDTO;
import com.accommodation.accommodation.model.Accommodation;
import com.accommodation.accommodation.service.AccommodationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accommodations")
public class AccommodationController {


    @Autowired
    private AccommodationService service;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public ResponseEntity<Accommodation> createAccommodation(@Valid @RequestBody AccomodationDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerAccommodation(objectMapper.convertValue(dto, Accommodation.class)));
    }


}
