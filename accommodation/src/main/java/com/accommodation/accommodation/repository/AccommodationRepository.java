package com.accommodation.accommodation.repository;

import com.accommodation.accommodation.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    Optional<Accommodation> findByIdUser(Long id);

    List<Accommodation> findByCheckInBetween(Date checkIn, Date checkOut);
}
