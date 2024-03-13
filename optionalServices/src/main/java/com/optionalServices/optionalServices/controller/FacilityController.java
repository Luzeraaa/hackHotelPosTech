package com.optionalServices.optionalServices.controller;

import com.optionalServices.optionalServices.entity.facility.Facility;
import com.optionalServices.optionalServices.dto.FacilityDTO;
import com.optionalServices.optionalServices.service.FacilityService;
import com.optionalServices.optionalServices.utils.Pagination;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/services")
public record FacilityController(FacilityService facilityService) {

    private static final String ALL = "all";
    private static final String FACILITY_ID_PATH = "/services";
    private static final String TEN = "10";
    private static final String ZERO = "0";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String FACILITY_MESSAGE = "Service updated successfully";

    @GetMapping(path = ALL)
    public ResponseEntity<Pagination<Facility>> getAllServices(
            @RequestParam(defaultValue = TEN) Integer limit,
            @RequestParam(defaultValue = ZERO) Integer offset) {
        var services = facilityService.getAllServices(limit, offset);
        return ResponseEntity.ok(services);
    }

    @GetMapping(path = {ID})
    public ResponseEntity<Optional<Facility>> getFacilityById(@RequestParam long id) {
        var services = facilityService.getFacilityById(id);
        return ResponseEntity.ok(services);
    }

    @PostMapping
    public ResponseEntity<Facility> resgisterFacility(
            @RequestBody
            FacilityDTO facilityDTO,
            UriComponentsBuilder uriBuilder) {
        var facility = facilityService.resgisterFacility(facilityDTO);
        return ResponseEntity
                .created(uriBuilder.path(FACILITY_ID_PATH).buildAndExpand(facility.getId()).toUri())
                .body(facility);
    }

    @PutMapping(params = {ID})
    public ResponseEntity<String> updateFacility(
            @RequestBody @Valid FacilityDTO facilityDTO,
            @RequestParam final Long id) {
        facilityService.updateFacility(id, facilityDTO);
        return ResponseEntity.ok(FACILITY_MESSAGE);
    }

    @GetMapping(params = {NAME})
    public ResponseEntity<List<Facility>> getServicesByName(@RequestParam String name) {
        var services = facilityService.getServicesByName(name);
        return ResponseEntity.ok(services);
    }

    @DeleteMapping(params = {ID})
    public ResponseEntity<Facility> deleteFacility(final @RequestParam Long id) {
        facilityService.deleteFacility(id);

        return ResponseEntity.noContent().build();
    }
}
