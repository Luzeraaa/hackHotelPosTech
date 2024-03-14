package com.roomManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingRequest {
    private Long userId;
    private List<Long> roomsId;
    private List<Long> itemsId;
    private List<Long> facilitiesId;
}
