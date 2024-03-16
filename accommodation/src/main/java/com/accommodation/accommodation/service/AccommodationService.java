package com.accommodation.accommodation.service;

import com.accommodation.accommodation.model.Accommodation;
import com.accommodation.accommodation.model.Room;
import com.accommodation.accommodation.model.TotalReservation;
import com.accommodation.accommodation.repository.AccommodationRepository;
import com.accommodation.accommodation.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationService {


    @Autowired
    private AccommodationRepository repository;

    @Autowired
    private RoomRepository roomRepository;


    @Transactional
    public Accommodation registerAccommodation(Accommodation accommodation, Long idUser, Long idRoom) {

        if (repository.findByIdUser(idUser).isPresent() && !checkFreeData(accommodation)) {
            throw new RuntimeException("Accommodation alredys existe for user");
        }

        var room = roomRepository.findById(idRoom).orElseThrow(() -> new EntityNotFoundException("Room not found"));

        if (accommodation.getTotalPeople() > room.getTotalPeople()) {
            throw new RuntimeException("Number of people exceeded the room limit");
        }

        accommodation.setRoom(room);
        accommodation.setIdUser(idUser);
        room.getAccommodations().add(accommodation);
        repository.save(accommodation);
        roomRepository.save(room);


        return accommodation;

    }

    private boolean checkFreeData(Accommodation accommodation) {
        if (accommodation.getCheckOut().before(accommodation.getCheckIn())) {
            throw new RuntimeException("Data do check out maior do que a data do check in");
        }

        List<Accommodation> existingAccommodations = repository.findByCheckInBeforeAndCheckOutAfter(
                accommodation.getCheckOut(), accommodation.getCheckIn());

        if (!existingAccommodations.isEmpty()) {
            throw new RuntimeException("There is already accommodation on that date");
        }

        return true;
    }

    public List<Accommodation> getAccommodationByUser(Long idUser) {
        return repository.findAllByIdUser(idUser);
    }

    public TotalReservation getTotalReservation(Long idUser) {

        var rooms = roomRepository.findAll()
                .stream()
                .filter(r -> r.getAccommodations()
                        .stream()
                        .anyMatch(a -> a.getIdUser().equals(idUser)))
                .collect(Collectors.toList());

        var reservations = 0;
        var total = BigDecimal.ZERO;
        var days = 0L;

        for (Room room : rooms) {
            for (Accommodation accommodation : room.getAccommodations()) {
                try {
                    var millisecondsInDay = 1000 * 60 * 60 * 24; // Número de milissegundos em um dia
                    var millisecondsCheckIn = accommodation.getCheckIn().getTime();
                    var millisecondsCheckOut = accommodation.getCheckOut().getTime();
                    days += (millisecondsCheckOut - millisecondsCheckIn) / millisecondsInDay;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("It was not possible to calculate the difference between check in and check out");
                }
                reservations += 1;
            }
            total = total.add(room.getValueDaily().multiply(BigDecimal.valueOf(days)));
            days = 0L;
        }

        return new TotalReservation(reservations, total, rooms);
    }
}
