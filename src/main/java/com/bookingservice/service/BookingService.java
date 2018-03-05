package com.bookingservice.service;

import com.bookingservice.entity.Booking;
import java.util.List;

public interface BookingService extends DataService<Booking>{

    List<Booking> getBookingByUser(long userId);

    List<Booking> getBookingOfRoomForBookingDate(Booking booking);

    double getUserBookingPrice(long userId);
}
