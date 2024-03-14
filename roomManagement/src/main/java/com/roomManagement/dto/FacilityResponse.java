package com.roomManagement.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter
public class FacilityResponse {
    private Long id;
    private String name;
    private Currency price;
}
