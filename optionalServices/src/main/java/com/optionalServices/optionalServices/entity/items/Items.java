package com.optionalServices.optionalServices.entity.items;

import com.optionalServices.optionalServices.dto.ItemsDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Currency;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    public Items(ItemsDTO itemsDTO) {
        this.name = itemsDTO.name();
        this.price = itemsDTO.price();
    }

    public void update(ItemsDTO dto){
        if (dto.name() != null) {
            this.name = dto.name();
        }

        if (dto.price() != null) {
            this.price = dto.price();
        }

    }
}
