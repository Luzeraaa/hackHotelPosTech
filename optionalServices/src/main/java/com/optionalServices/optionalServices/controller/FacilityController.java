package com.optionalServices.optionalServices.controller;

import com.optionalServices.optionalServices.entity.facility.Facility;
import com.optionalServices.optionalServices.dto.FacilityDTO;
import com.optionalServices.optionalServices.service.FacilityService;
import com.optionalServices.optionalServices.utils.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/facility")
public record FacilityController(FacilityService facilityService) {

    private static final String ALL = "all";
    private static final String FACILITY_ID_PATH = "/facility";
    private static final String TEN = "10";
    private static final String ZERO = "0";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String FACILITY_MESSAGE = "Facility updated successfully";

    @GetMapping(path = ALL)
    public ResponseEntity<Pagination<Facility>> getAllFacility(
            @RequestParam(defaultValue = TEN) Integer limit,
            @RequestParam(defaultValue = ZERO) Integer offset) {
        var services = facilityService.getAllFacility(limit, offset);
        return ResponseEntity.ok(services);
    }

    @GetMapping
    public ResponseEntity<Optional<Facility>> getFacilityById(@RequestParam long id) {
        var services = facilityService.getFacilityById(id);
        return ResponseEntity.ok(services);
    }

    @PostMapping
    public ResponseEntity<Facility> registerFacility(
            @RequestBody
            FacilityDTO facilityDTO,
            UriComponentsBuilder uriBuilder) {
        var facility = facilityService.registerFacility(facilityDTO);
        return ResponseEntity
                .created(uriBuilder.path(FACILITY_ID_PATH).buildAndExpand(facility.getId()).toUri())
                .body(facility);
    }

    @PutMapping(params = {ID})
    public ResponseEntity<String> updateFacility(
            @RequestBody
            FacilityDTO facilityDTO,
            @RequestParam final Long id) {
        facilityService.updateFacility(id, facilityDTO);
        return ResponseEntity.ok(FACILITY_MESSAGE);
    }

    @GetMapping(params = {NAME})
    public ResponseEntity<List<Facility>> getFacilityByName(@RequestParam String name) {
        var services = facilityService.getFacilityByName(name);
        return ResponseEntity.ok(services);
    }

    @DeleteMapping(params = {ID})
    public ResponseEntity<Facility> deleteFacility(final @RequestParam Long id) {
        facilityService.deleteFacility(id);

        return ResponseEntity.noContent().build();
    }
}