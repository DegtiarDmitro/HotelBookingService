package com.bookingservice.service;

import com.bookingservice.entity.Room;
import java.util.List;

public interface RoomService extends DataService<Room> {
    List<Room> getRoomsByCategory(long categoryId);
}
