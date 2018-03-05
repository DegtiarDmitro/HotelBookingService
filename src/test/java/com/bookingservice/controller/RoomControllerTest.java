package com.bookingservice.controller;


import com.bookingservice.entity.*;
import com.bookingservice.service.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RoomControllerTest {



    @Mock
    private RoomService roomService;

    @Spy
    @InjectMocks
    private RoomController roomController;


    private Room room;


    @Before
    public void initBooking(){

        Hotel hotel = new Hotel("Premier Hotel", "233-244-23", "City, street 5");
        RoomCategory roomCategory = new RoomCategory("STD");
        roomCategory.setId(1);
        room = new Room(48, 130, roomCategory, hotel);
    }


    @Test
    public void getRoomsTest() throws Exception {

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        when(roomService.getAll()).thenReturn(rooms);

        List<Room> foundRooms = roomController.getRooms();
        assertEquals(foundRooms.size(), 1);
        verify(roomService, times(1)).getAll();
    }

    @Test
    public void getRoomsByCategory() throws Exception {

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        when(roomService.getRoomsByCategory(anyLong())).thenReturn(rooms);

        List<Room> foundRooms = roomController.getRoomsByCategory(1);
        assertEquals(foundRooms.size(), 1);
        assertEquals(foundRooms.get(0).getCategory().getName(), "STD");
        verify(roomService, times(1)).getRoomsByCategory(1);
    }



}
