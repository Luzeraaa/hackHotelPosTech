package com.optionalServices.optionalServices.controller;

import com.optionalServices.optionalServices.entity.facility.Facility;
import com.optionalServices.optionalServices.dto.FacilityDTO;
import com.optionalServices.optionalServices.service.FacilityService;
import com.optionalServices.optionalServices.utils.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/services")
public record FacilityController(FacilityService facilityService) {

    private static final String ALL = "all";
    private static final String SERVICES_ID_PATH = "/services";
    private static final String TEN = "10";
    private static final String ZERO = "0";
    private static final String ID = "id";
    private static final String NAME= "name";
    private static final String SERVICES_MESSAGE = "Service updated successfully";

    @GetMapping(path = ALL)
    public ResponseEntity<Pagination<Facility>> getAllServices(
            @RequestParam(defaultValue = TEN) Integer limit,
            @RequestParam(defaultValue = ZERO) Integer offset) {
        var services = facilityService.getAllServices(limit, offset);
        return ResponseEntity.ok(services);
    }

    @GetMapping(path = {ID})
    public ResponseEntity<Optional<Facility>> getFacilityById(@RequestParam long id){
        var services = facilityService.getFacilityById(id);
        return ResponseEntity.ok(services);
    }

    @PostMapping
    public ResponseEntity<Facility> resgisterFacility(@RequestBody FacilityDTO facilityDTO){
        var facility = facilityService.resgisterfacility(facilityDTO);
        return ResponseEntity
                .created(uriBuilder.path(SERVICES_ID_PATH).buildAndExpand(facility.getId()).toUri())
                .body(facility);
    }

    @PutMapping
    public ResponseEntity<String> updateServices(
            @RequestBody FacilityDTO facilityDTO
            @RequestParam final Long id){
            facilityService.update(id, facilityDTO);
        return ResponseEntity.ok(SERVICES_MESSAGE);
    }

    @GetMapping(params = {NAME})
    public ResponseEntity<List<Facility>> getServicesByName(@RequestParam String name) {
        var services = facilityService.getServicesByName(name);
        return ResponseEntity.ok(services);
    }

    @DeleteMapping(params = {ID})
    public ResponseEntity<Facility> deleteServices(final @RequestParam Long id) {
        facilityService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
