package com.bookingservice.service.impl;


import com.bookingservice.dao.BookingRepository;
import com.bookingservice.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Spy
    @InjectMocks
    private BookingServiceImpl bookingServiceImpl;

    private Booking booking;
    private User user;
    private Room room;

    @Before
    public void initBooking(){
        user = new User("TestUser", "testuser@gmail.com", "1111", UserRole.CLIENT);
        user.setId(1);
        Hotel hotel = new Hotel("Premier Hotel", "233-244-23", "City, street 5");
        RoomCategory roomCategory = new RoomCategory("STD");
        room = new Room(48, 130, roomCategory, hotel);
        AdditionalService breakfast = new AdditionalService("breakfast", 20);
        List<AdditionalService> services = new ArrayList<>();
        services.add(breakfast);
        room.setServices(services);
        booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setStartDate(Date.valueOf(LocalDate.of(2018, 5, 20)));
        booking.setEndDate(Date.valueOf(LocalDate.of(2018,6,9)));

    }



    @Test
    public void getBookingByUserTest(){
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        when(bookingRepository.getBookingByUser(anyLong())).thenReturn(bookings);

        List<Booking> savedBookings = bookingServiceImpl.getBookingByUser(user.getId());
        assertEquals(1, savedBookings.size());
        assertEquals(user.getId(), savedBookings.get(0).getUser().getId());
        verify(bookingRepository, times(1)).getBookingByUser(anyLong());
    }


    @Test
    public void getUserBookingPrice(){
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        when(bookingRepository.getBookingByUser(anyLong())).thenReturn(bookings);

        double totalPrice = bookingServiceImpl.getUserBookingPrice(user.getId());
        assertEquals(3000,totalPrice,0.01);
        verify(bookingRepository, times(1)).getBookingByUser(anyLong());
    }


    @Test
    public void getBookingOfRoomForBookingDate(){

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        when(bookingRepository.getBookingByRoom(anyLong())).thenReturn(bookings);

        Booking testBooking = new Booking();
        testBooking.setUser(user);
        testBooking.setRoom(room);
        testBooking.setStartDate(Date.valueOf(LocalDate.of(2018,5,22)));
        testBooking.setEndDate(Date.valueOf(LocalDate.of(2018,6,15)));


        List<Booking> findedBookings = bookingServiceImpl.getBookingOfRoomForBookingDate(testBooking);
        assertEquals(1, findedBookings.size());
        verify(bookingRepository, times(1)).getBookingByRoom(anyLong());
    }



}
