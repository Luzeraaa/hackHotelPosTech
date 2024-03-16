package com.roomManagement.controller;

import com.roomManagement.dto.SummaryResponse;
import com.roomManagement.service.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SummaryController")
@RestController
@RequestMapping("roomManagement")
public class SummaryController {


  private final BookingService bookingService;

  public SummaryController(final BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @GetMapping("/summary/{userId}")
  public ResponseEntity<SummaryResponse> summary(@PathVariable final Long userId) {
    return ResponseEntity.ok(bookingService.getSummary(userId));
  }
}
