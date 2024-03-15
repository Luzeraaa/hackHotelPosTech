package com.accommodation.accommodation.controllers.dto;

import com.accommodation.accommodation.model.enums.RoomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RoomUpdateDTO(

        @Enumerated(EnumType.STRING)
        RoomType roomType,

        @Positive
        Integer totalPeople,

        @Positive
        Integer totalBeds,

        @Positive
        Integer bathroom,

        @Positive
        BigDecimal valueDaily,

        @Positive
        Integer amount

) {
}
