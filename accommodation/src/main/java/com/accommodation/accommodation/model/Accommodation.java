package com.accommodation.accommodation.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Table(name = "accommodation")
@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUser;

    @Temporal(TemporalType.DATE)
    private Date checkIn;

    @Temporal(TemporalType.DATE)
    private Date checkOut;

    private Integer totalPeople;

    @OneToOne(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private Room room;
}
