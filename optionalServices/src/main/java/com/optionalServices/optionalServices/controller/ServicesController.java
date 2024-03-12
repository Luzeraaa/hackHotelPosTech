package com.optionalServices.optionalServices.controller;

import com.optionalServices.optionalServices.Entity.Services.Services;
import com.optionalServices.optionalServices.service.ServicesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/services")
public record ServicesController(ServicesService service) {

    private static final String ALL = "all";
    private static final String TEN = "10";
    private static final String ZERO = "0";

@GetMapping(path = ALL)
    public ResponseEntity<Services> getAllServices(
            @RequestParam(defaultValue = TEN)Integer limit,
            @RequestParam(defaultValue = ZERO)Integer offset){
        var services = service.getAllServices(limit, offset);
        return ResponseEntity.ok(services);
}

}
