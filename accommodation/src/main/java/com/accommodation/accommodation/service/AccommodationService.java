package com.accommodation.accommodation.service;

import com.accommodation.accommodation.controllers.dto.AccommodationUpdateDTO;
import com.accommodation.accommodation.model.Accommodation;
import com.accommodation.accommodation.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;

    public Accommodation registerAccommodation(Accommodation accommodation) {
        if (accommodationRepository.findByName(accommodation.getName()) != null) {
            throw new RuntimeException("Accommodation alredys exist");
        }
        return accommodationRepository.save(accommodation);
    }

    public List<Accommodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }

    public void deleteAccommodation(Long id) {
        accommodationRepository.findById(id).ifPresentOrElse(
                a -> accommodationRepository.delete(a),
                () -> {
                    throw new RuntimeException("Accommodation not foud");
                }
        );
    }

    public Accommodation updateAccommodation(AccommodationUpdateDTO dto, Long id) {
        Optional<Accommodation> existingAccommodation = accommodationRepository.findById(id);

        if (existingAccommodation.isEmpty()) {
            throw new RuntimeException("Accommodation not found");
        }

        var accommodation = existingAccommodation.get();
        accommodation.update(dto);
        return accommodationRepository.save(accommodation);

    }
}
