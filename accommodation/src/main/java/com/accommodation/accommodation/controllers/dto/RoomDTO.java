package com.accommodation.accommodation.controllers.dto;

import com.accommodation.accommodation.model.enums.RoomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RoomDTO(

        @Enumerated(EnumType.STRING)
        @NotNull
        RoomType roomType,

        @NotNull
        @Positive
        Integer totalPeople,

        @NotNull
        @Positive
        Integer totalBeds,

        @NotNull
        @Positive
        Integer bathroom,

        @NotNull
        @Positive
        BigDecimal valueDaily,

        @NotNull
        @Positive
        Integer amount

) {
}
