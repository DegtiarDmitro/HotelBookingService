package com.bookingservice.controller;


import com.bookingservice.entity.Booking;
import com.bookingservice.exeption.NotCorrectRequestException;
import com.bookingservice.service.BookingService;
import com.bookingservice.service.RoomService;
import com.bookingservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/bookings")
public class BookingController {



    private final UserService userService;
    private final RoomService roomService;
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService, UserService userService, RoomService roomService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.roomService = roomService;
    }


    /**
     * Method return the list of all booking that are present
     * @return - List of Booking
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> getBookings(){
        return bookingService.getAll();
    }


    /**
     * Method return the concrete booking by id
     * @return - Booking
     */
    @RequestMapping(value = {"/booking/{id}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Booking getBooking(@PathVariable("id") long id){
        return bookingService.get(id);
    }

    /**
     *
     * @param booking - method expected json object that contain data necessary to convert it to Booking class
     * @param result - here keeps result of binding and errors of it (if they are)
     * @param response -
     * @return -
     * @throws BindException -
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@Valid @RequestBody Booking booking, BindingResult result, HttpServletResponse response) throws BindException, IOException, NotCorrectRequestException {

        if(result.hasErrors()){
            throw new BindException(result);
        }
        if(!roomService.exists(booking.getRoom().getId())){
            throw new NotCorrectRequestException(String.format("Requested room with id %d is not exists", booking.getRoom().getId()));
        }
        if(!userService.exists(booking.getUser().getId())){
            throw new NotCorrectRequestException(String.format("Requested user with id %d is not exists", booking.getUser().getId()));
        }
        if(!bookingService.getBookingOfRoomForBookingDate(booking).isEmpty()){
            throw new NotCorrectRequestException("The specified dates are busy");
        }
        bookingService.add(booking);
        response.setHeader("Location", String.format("/bookings/booking/%d", booking.getId()));
        return booking;
    }

    /**
     * Method returning the list of all user booking
     * @param userId - user id
     * @return - List of Booking for concrete user
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Booking> getUserBooking(@PathVariable("id") long userId){
        return bookingService.getBookingByUser(userId);
    }


    /**
     * Method calculating the total price of the user booking
     * @param userId - user id
     * @return - double value - total price of user booking
     */
    @RequestMapping(value = "/user/{id}/price", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public double getUserBookingPrice(@PathVariable("id") long userId){
        return bookingService.getUserBookingPrice(userId);
    }



}
