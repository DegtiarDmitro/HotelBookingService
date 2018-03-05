package com.bookingservice.controller;


import com.bookingservice.entity.Room;
import com.bookingservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/rooms")
public class RoomController {


    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    /**
     * Method return list of all rooms
     * @return - List of Rooms
     */
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Room> getRooms() {
        return roomService.getAll();
    }


    /**
     * Method return the list of Room by Category
     * @param categoryId -  room category id
     * @return - List of Room
     */
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Room> getRoomsByCategory(@PathVariable("id") long categoryId) {
        return roomService.getRoomsByCategory(categoryId);
    }



}
