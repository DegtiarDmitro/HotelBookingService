package com.bookingservice.dao;

import com.bookingservice.entity.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RoomRepository extends DataRepository<Room> {

    @Query(value = "SELECT r FROM Room r WHERE r.category.id = :categoryId")
    List<Room> getRoomsByCategory(@Param("categoryId")long categoryId);
}
