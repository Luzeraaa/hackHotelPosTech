package com.roomManagement.gateway;

import com.roomManagement.dto.SummaryResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public record AccommodationGateway() {

  public SummaryResponse getSummaryResponse(Long userId) {
    return new SummaryResponse(
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(3),
            Arrays.asList("1L", "2L", "3L"),
            Arrays.asList("1L", "2L", "3L"),
            Arrays.asList("1L", "2L", "3L"),
            129.50
    );
  }
}
