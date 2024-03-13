package com.accommodation.accommodation.model;

import com.accommodation.accommodation.model.enums.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "room")
@Entity
@EqualsAndHashCode(of = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    private Long id;

    private RoomType roomType;

    private Integer totalPeople;

    private Integer totalBeds;

    private Integer bathroom;

    private BigDecimal valueDaily;

    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;
}
