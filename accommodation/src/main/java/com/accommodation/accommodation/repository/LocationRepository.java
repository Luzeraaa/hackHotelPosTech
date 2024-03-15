package com.accommodation.accommodation.repository;

import com.accommodation.accommodation.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findByName(String name);

}
