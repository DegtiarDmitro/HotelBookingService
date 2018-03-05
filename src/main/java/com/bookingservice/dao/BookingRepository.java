package com.bookingservice.dao;

import com.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookingRepository extends DataRepository<Booking> {

    @Query(value = "SELECT b FROM Booking b WHERE b.user.id = :userId")
    List<Booking> getBookingByUser(@Param("userId") long userId);

    @Query(value = "SELECT b FROM Booking b WHERE b.room.id = :roomId")
    List<Booking> getBookingByRoom(@Param("roomId") long roomId);

}
