package com.roomManagement.controller;

import com.roomManagement.dto.SummaryResponse;
import com.roomManagement.entity.Booking;
import com.roomManagement.service.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "SummaryController")
@RestController
@RequestMapping("roomManagement")
public class SummaryController {
    @Autowired
    private final RestTemplate restTemplate;

    @Autowired
    private final BookingService bookingService;

    public SummaryController(final RestTemplate restTemplate, final BookingService bookingService) {
        this.restTemplate = restTemplate;
        this.bookingService = bookingService;
    }

    //Sistema mostra os itens selecionados, incluindo datas, tipos de quartos e opcionais, e também o
    //valor total.
    @GetMapping("/summary/userId")
    public ResponseEntity<SummaryResponse> summary(@PathVariable final Long userId) {
        final Booking booking = bookingService.findByUserId(userId);

        final SummaryResponse summaryResponse = new SummaryResponse();

        booking.getRoomsId().forEach(roomId -> summaryResponse.getRooms().add(restTemplate.getForObject("accommodations/" + roomId, String.class)));

        booking.getItemsId().forEach(itemId -> summaryResponse.getRooms().add(restTemplate.getForObject("accommodations/" + itemId, String.class)));

        booking.getServicesId().forEach(serviceId -> summaryResponse.getRooms().add(restTemplate.getForObject("accommodations/" + serviceId, String.class)));

        return ResponseEntity.ok(summaryResponse);
    }
}
