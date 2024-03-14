package com.accommodation.accommodation.repository;

import com.accommodation.accommodation.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    Accommodation findByName(String name);

}
