package com.roomManagement.dto;

import com.roomManagement.dto.user.UserDTO;

import java.util.List;

public record PaginationDTO(
        List<UserDTO> results
) {
}
