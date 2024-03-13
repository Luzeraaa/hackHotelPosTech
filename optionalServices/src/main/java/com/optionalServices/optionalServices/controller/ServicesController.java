package com.optionalServices.optionalServices.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.optionalServices.optionalServices.Entity.Services.Services;
import com.optionalServices.optionalServices.dto.ServicesDTO;
import com.optionalServices.optionalServices.service.ServicesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/services")
public record ServicesController(ServicesService service) {

    private static final String ALL = "all";
    private static final String SERVICES_ID_PATH = "/services";
    private static final String TEN = "10";
    private static final String ZERO = "0";
    private static final String ID = "id";
    private static final String NAME= "name";
    private static final String SERVICES_MESSAGE = "Service updated successfully";

    @GetMapping(path = ALL)
    public ResponseEntity<Services> getAllServices(
            @RequestParam(defaultValue = TEN) Integer limit,
            @RequestParam(defaultValue = ZERO) Integer offset) {
        var services = service.getAllServices(limit, offset);
        return ResponseEntity.ok(services);
    }


    @GetMapping(path = {ID})
    public ResponseEntity<Services> getServicesById(@RequestParam long id){
        var services = service.getServicesById(id);
        return ResponseEntity.ok(services);
    }

    @PostMapping
    public ResponseEntity<Services> resgisterServices(@RequestBody ServicesDTO servicesDTO){
        var services = service.resgisterServices(servicesDTO);
        return ResponseEntity
                .created(uriBuilder.path(SERVICES_ID_PATH).buildAndExpand(service.getId()).toUri())
                .body(services);
    }

    @PutMapping
    public ResponseEntity<String> updateServices(
            @RequestBody ServicesDTO servicesDTO
            @RequestParam final Long id){
            service.update(id, servicesDTO);
        return ResponseEntity.ok(SERVICES_MESSAGE);
    }

    @GetMapping(params = {NAME})
    public ResponseEntity<List<Services>> getServicesByName(@RequestParam String name) {
        var services = service.getServicesByName(name);
        return ResponseEntity.ok(services);
    }

    @DeleteMapping(params = {ID})
    public ResponseEntity<Services> deleteServices(final @RequestParam Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
