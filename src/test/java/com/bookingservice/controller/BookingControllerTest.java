package com.bookingservice.controller;


import com.bookingservice.entity.*;
import com.bookingservice.service.BookingService;
import com.bookingservice.service.RoomService;
import com.bookingservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @Mock
    private RoomService roomService;

    @Mock
    private UserService userService;

    @Mock
    private HttpServletResponse response;

    @Mock
    private BindingResult bindingResult;

    @Spy
    @InjectMocks
    private BookingController bookingController;

    private Booking booking;
    private User user;

    @Before
    public void initBooking(){
        user = new User();
        user.setId(1);
        Room room = new Room(48, 130, new RoomCategory(), new Hotel());
        room.setId(1);
        booking = new Booking();
        booking.setId(1);
        booking.setUser(user);
        booking.setRoom(room);
        booking.setStartDate(Date.valueOf(LocalDate.of(2018,5,20)));
        booking.setEndDate(Date.valueOf(LocalDate.of(2018,6,10)));
    }



    @Test
    public void getBookings() throws Exception {

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        when(bookingService.getAll()).thenReturn(bookings);

        List<Booking> foundBookings = bookingController.getBookings();
        assertEquals(foundBookings.size(), 1);
        verify(bookingService, times(1)).getAll();

    }

    @Test
    public void getBooking() throws Exception {
        when(bookingService.get(anyLong())).thenReturn(booking);

        Booking foundBooking = bookingController.getBooking(user.getId());
        assertEquals(foundBooking, booking);
        verify(bookingService, times(1)).get(anyLong());
    }

    @Test
    public void createBooking() throws Exception {

        when(bookingService.add(any(Booking.class))).thenReturn(booking);
        when(userService.exists(anyLong())).thenReturn(true);
        when(roomService.exists(anyLong())).thenReturn(true);
        when(bookingService.getBookingOfRoomForBookingDate(any(Booking.class))).thenReturn(new ArrayList<>());

        Booking savedBooking = bookingController.createBooking(booking, bindingResult, response);
        assertEquals(booking, savedBooking);
        verify(bookingService, times(1)).add(any(Booking.class));
        verify(bookingService, times(1)).getBookingOfRoomForBookingDate(any(Booking.class));
        verify(userService, times(1)).exists(anyLong());
        verify(roomService, times(1)).exists(anyLong());
    }

    @Test
    public void getUserBooking() throws Exception {

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        when(bookingService.getBookingByUser(anyLong())).thenReturn(bookings);

        List<Booking> foundBookings = bookingController.getUserBooking(user.getId());
        assertEquals(foundBookings.size(), 1);
        verify(bookingService, times(1)).getBookingByUser(anyLong());
    }

    @Test
    public void getUserBookingPrice() throws Exception {

        when(bookingService.getUserBookingPrice(anyLong())).thenReturn(1000D);

        double totalPrice = bookingController.getUserBookingPrice(user.getId());
        assertEquals(totalPrice, 1000D, 0.01);
        verify(bookingService, times(1)).getUserBookingPrice(anyLong());
    }


}
