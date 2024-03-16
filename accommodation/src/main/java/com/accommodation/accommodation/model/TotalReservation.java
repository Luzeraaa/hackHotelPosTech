package com.accommodation.accommodation.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Data
public class TotalReservation {

    private Integer reservations;
    private BigDecimal total;
    private List<Room> rooms;
}
