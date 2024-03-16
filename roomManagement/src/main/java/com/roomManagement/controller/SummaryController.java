package com.roomManagement.controller;

import com.roomManagement.dto.FacilityResponse;
import com.roomManagement.dto.ItemResponse;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

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
    @GetMapping("/summary/{userId}")
    public ResponseEntity<SummaryResponse> summary(@PathVariable final Long userId) {
        final Booking booking =  new Booking(1L, 1L,  Arrays.asList(1L, 2L, 3L),  Arrays.asList(1L, 2L),  Arrays.asList(1L, 2L, 3L));
                // bookingService.findByUserId(userId);


       //  final SummaryResponse summaryResponse = new SummaryResponse();

        // booking.getRoomsId().forEach(roomId -> summaryResponse.getRooms().add(restTemplate.getForObject("accommodations/" + roomId, String.class)));

        AtomicReference<Double> total = new AtomicReference<>(0d);

//        booking.getItemsId().forEach(itemId -> {
//            final ItemResponse itemResponse = restTemplate.getForObject("http://localhost:8083/items?id=" + itemId, ItemResponse.class);
//
//            assert itemResponse != null;
//
//            summaryResponse.getRooms().add(itemResponse.getName());
//
//            total.updateAndGet(value -> value + itemResponse.getPrice());
//        });

//        booking.getFacilitiesId().forEach(facilityId -> {
//            final FacilityResponse facilityResponse = restTemplate.getForObject("http://localhost:8083/facility?id=" + facilityId, FacilityResponse.class);
//
//            assert facilityResponse != null;
//
//            summaryResponse.getRooms().add(facilityResponse.getName());
//
//            total.updateAndGet(value -> value + facilityResponse.getPrice());
//        });

      //  summaryResponse.setTotal(total.get());

        return ResponseEntity.ok(new SummaryResponse(
                LocalDateTime.now(),
                LocalDateTime.now(),
                Arrays.asList("1L", "2L", "3L"),
                Arrays.asList("1L", "2L", "3L"),
                Arrays.asList("1L", "2L", "3L"),
                102.0
        ));
    }
}
