package com.accommodation.accommodation.controllers.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RoomsDTO(
        @NotNull
        List<RoomDTO> rooms

) {
}
