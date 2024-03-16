package com.accommodation.accommodation.controllers;

import com.accommodation.accommodation.controllers.dto.AmenitieUpdateDTO;
import com.accommodation.accommodation.controllers.dto.BuildingsDTO;
import com.accommodation.accommodation.model.Building;
import com.accommodation.accommodation.model.Buildings;
import com.accommodation.accommodation.service.BuildingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BuildingService service;

    @PostMapping("/{idAccommodation}")
    public ResponseEntity<List<Building>> registerBuilding(@Valid @RequestBody BuildingsDTO dto, @PathVariable Long idAccommodation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerBuilding(objectMapper.convertValue(dto, Buildings.class).getBuildings(), idAccommodation));
    }

    @GetMapping("/{idLocation}")
    public ResponseEntity<List<Building>> getAllBuildingsByLocation(@PathVariable Long idLocation) {
        return ResponseEntity.ok().body(service.getBuildingByLocation(idLocation));
    }

    @PatchMapping("/{idBuilding}")
    public ResponseEntity<Building> updateBuilding(@RequestBody AmenitieUpdateDTO dto, @PathVariable Long idBuilding) {
        return ResponseEntity.ok().body(service.updateBuilding(dto, idBuilding));
    }

    @DeleteMapping("/{idBuilding}")
    public ResponseEntity<Void> updateAmenitie(@PathVariable Long idBuilding) {
        service.deleteBuilding(idBuilding);
        return ResponseEntity.noContent().build();
    }

}
