package com.roomManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SummaryResponse {
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private List<String> rooms;
    private List<String> items;
    private List<String> facilities;
    private Double total;
}
