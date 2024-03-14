package com.roomManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SummaryResponse {
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private List<String> rooms;
    private List<String> items;
    private List<String> facilities;
    private Double total;
}
