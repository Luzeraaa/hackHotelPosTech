package com.roomManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roomManagement")
public class TotalAccommodationController {
    @PostMapping("/totalAccommodation")
    public ResponseEntity<String> totalAccommodation(@RequestBody final Object object) {
        return null;
    }
}
