package com.accommodation.accommodation.controllers;

import com.accommodation.accommodation.controllers.dto.AmenitieUpdateDTO;
import com.accommodation.accommodation.controllers.dto.AmenitiesDTO;
import com.accommodation.accommodation.model.Amenitie;
import com.accommodation.accommodation.model.Amenities;
import com.accommodation.accommodation.service.AmenitieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amenities")
public class AmenitieController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private AmenitieService service;

    @PostMapping("/{idAccommodation}")
    public ResponseEntity<List<Amenitie>> registerAmenitie(@Valid @RequestBody AmenitiesDTO dto, @PathVariable Long idAccommodation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerAmenitie(objectMapper.convertValue(dto, Amenities.class).getAmenities(), idAccommodation));
    }

    @GetMapping("/{idAccomodation}")
    public ResponseEntity<List<Amenitie>> getAmenitieByLocation(@PathVariable Long idAccomodation) {
        return ResponseEntity.ok().body(service.getAmenitieByAccommodation(idAccomodation));
    }

    @PatchMapping("/{idAmenitie}")
    public ResponseEntity<Amenitie> updateAmenitie(@RequestBody AmenitieUpdateDTO dto, @PathVariable Long idAmenitie) {
        return ResponseEntity.ok().body(service.updateAmenitie(dto, idAmenitie));
    }

    @DeleteMapping("/{idAmenitie}")
    public ResponseEntity<Void> deleteAmenitie(@PathVariable Long idAmenitie) {
        service.deleteAmenitie(idAmenitie);
        return ResponseEntity.noContent().build();
    }


}
