package com.optionalServices.optionalServices.repository.facility;

import com.optionalServices.optionalServices.entity.facility.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long>{
            List<Facility> findByName(String name);
}
