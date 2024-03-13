package com.optionalServices.optionalServices.repository.facility;

import com.optionalServices.optionalServices.entity.facility.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long>{
            Optional<Facility> findByName(String name);
}
