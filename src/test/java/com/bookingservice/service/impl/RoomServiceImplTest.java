package com.bookingservice.service.impl;


import com.bookingservice.WebApplication;
import com.bookingservice.dao.RoomRepository;
import com.bookingservice.entity.Hotel;
import com.bookingservice.entity.Room;
import com.bookingservice.entity.RoomCategory;
import com.bookingservice.service.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class )
public class RoomServiceImplTest {


    @Autowired
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;


    @InjectMocks
    private RoomServiceImpl roomServiceImpl;


    private Hotel hotel;
    private RoomCategory roomCategory;
    private Room room;

    @Before
    public void initRoom(){
        hotel = new Hotel("Premier Hotel", "233-244-23", "City, street 5");
        roomCategory = new RoomCategory("STD");
        roomCategory.setId(1);
        room = new Room(48, 130.5, roomCategory, hotel);
    }


    @Test
    public void getRoomByCategoryTest(){

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        when(roomRepository.getRoomsByCategory(anyLong())).thenReturn(rooms);
        List<Room> savedRooms = roomServiceImpl.getRoomsByCategory(roomCategory.getId());
        assertEquals(savedRooms.size(), 1);
        assertEquals(savedRooms.get(0).getCategory().getId(), roomCategory.getId());
        verify(roomRepository, times(1)).getRoomsByCategory(anyLong());
    }



}
