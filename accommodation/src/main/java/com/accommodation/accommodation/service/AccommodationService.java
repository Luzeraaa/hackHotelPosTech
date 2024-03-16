package com.accommodation.accommodation.service;

import com.accommodation.accommodation.model.Accommodation;
import com.accommodation.accommodation.repository.AccommodationRepository;
import com.accommodation.accommodation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationService {


    @Autowired
    private AccommodationRepository repository;

    @Autowired
    private RoomRepository roomRepository;


    public Accommodation registerAccommodation(Accommodation accommodation, Long idUser, Long idRoom) {

        checkData(accommodation);
        if (repository.findByIdUser(idUser).isPresent()) {
            throw new RuntimeException("Accommodation alredys existe for user");
        }

        var room = roomRepository.findById(idRoom).orElseThrow(() -> new RuntimeException("Room not found"));

        if (accommodation.getTotalPeople() > room.getTotalPeople()) {
            throw new RuntimeException("Number of people exceeded the room limit");
        }
        accommodation.setRoom(room);
        accommodation.setIdUser(idUser);
        return repository.save(accommodation);
    }

    private void checkData(Accommodation accommodation) {
        List<Accommodation> existingAccommodations = repository.findByCheckInBeforeAndCheckOutAfter(
                accommodation.getCheckOut(), accommodation.getCheckIn());

        if (!existingAccommodations.isEmpty()) {
            throw new RuntimeException("There is already accommodation on that date");
        }
    }

    public List<Accommodation> getAccommodationByUser(Long idUser) {

        return repository.findAllByIdUser(idUser);
    }
}
