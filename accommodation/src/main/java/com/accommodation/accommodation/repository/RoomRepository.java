package com.accommodation.accommodation.repository;

import com.accommodation.accommodation.model.Room;
import com.accommodation.accommodation.model.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findFirstByRoomTypeAndBuildingId(RoomType roomType, Long idBuilding);

    List<Room> findAllByBuildingId(Long idBuilding);
}
