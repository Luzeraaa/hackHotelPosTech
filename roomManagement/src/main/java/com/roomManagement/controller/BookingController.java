package com.roomManagement.controller;

import com.roomManagement.dto.BookingRequest;
import com.roomManagement.entity.Booking;
import com.roomManagement.service.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "BookingController")
@RestController
@RequestMapping("roomManagement")
public class BookingController {
    @Autowired
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/booking")
    public ResponseEntity<Booking> save(@RequestBody final BookingRequest bookingRequest) {
        final Booking booking = new Booking();
        booking.setUserId(bookingRequest.getUserId());
        booking.setRoomsId(bookingRequest.getRoomsId());
        booking.setItemsId(bookingRequest.getItemsId());
        booking.setFacilitiesId(bookingRequest.getFacilitiesId());

        return ResponseEntity.ok(bookingService.save(booking));
    }
}
