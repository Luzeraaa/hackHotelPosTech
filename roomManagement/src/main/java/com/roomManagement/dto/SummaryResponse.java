package com.roomManagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SummaryResponse {
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private List<String> rooms;
    private List<String> optional;
    private BigDecimal total;
}
