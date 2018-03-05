package com.bookingservice.service.impl;

import com.bookingservice.dao.DataRepository;
import com.bookingservice.dao.RoomRepository;
import com.bookingservice.entity.Room;
import com.bookingservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomServiceImpl extends DataServiceImpl<Room> implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(DataRepository<Room> dataRepository, RoomRepository roomRepository) {
        super(dataRepository);
        this.roomRepository = roomRepository;
    }


    @Override
    public List<Room> getRoomsByCategory(long categoryId) {
        return roomRepository.getRoomsByCategory(categoryId);
    }
}
