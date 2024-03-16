package com.accommodation.accommodation.model;

import com.accommodation.accommodation.controllers.dto.RoomUpdateDTO;
import com.accommodation.accommodation.model.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Table(name = "room")
@Entity
@EqualsAndHashCode(of = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private Integer totalPeople;

    private Integer totalBeds;

    private Integer bathroom;

    private BigDecimal valueDaily;

    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    @JsonIgnore
    private Building building;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Accommodation> accommodations;


    public void update(RoomUpdateDTO dto) {
        if (dto.roomType() != null) {
            this.roomType = dto.roomType();
        }

        if (dto.totalPeople() != null) {
            this.totalPeople = dto.totalPeople();
        }

        if (dto.totalBeds() != null) {
            this.totalBeds = dto.totalBeds();
        }

        if (dto.bathroom() != null) {
            this.bathroom = dto.bathroom();
        }

        if (dto.valueDaily() != null) {
            this.valueDaily = dto.valueDaily();
        }

        if (dto.bathroom() != null) {
            this.amount = dto.amount();
        }
    }
}
