package com.accommodation.accommodation.repository;

import com.accommodation.accommodation.model.Amenitie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmenitieRepository extends JpaRepository<Amenitie, Long> {

    Amenitie findByName(String name);

    List<Amenitie> getAllByAccommodationId(Long idAccommodation);
}
