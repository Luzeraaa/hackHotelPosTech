package com.accommodation.accommodation.controllers;


import com.accommodation.accommodation.controllers.dto.AccommodationDTO;
import com.accommodation.accommodation.model.Accommodation;
import com.accommodation.accommodation.service.AccommodationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accommodations")
public class AccommodationController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AccommodationService service;


    @PostMapping("/{idUsuario}/{idRoom}")
    public ResponseEntity<Accommodation> registerAccommodation(@Valid @RequestBody AccommodationDTO dto, @PathVariable Long idUsuario, @PathVariable Long idRoom){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerAccommodation(objectMapper.convertValue(dto, Accommodation.class), idUsuario, idRoom));

    }

}
