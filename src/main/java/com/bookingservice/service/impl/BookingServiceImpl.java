package com.bookingservice.service.impl;

import com.bookingservice.dao.BookingRepository;
import com.bookingservice.dao.DataRepository;
import com.bookingservice.entity.Booking;
import com.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl extends DataServiceImpl<Booking> implements BookingService {


    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(DataRepository<Booking> dataRepository, BookingRepository bookingRepository) {
        super(dataRepository);
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getBookingByUser(long userId) {
        return bookingRepository.getBookingByUser(userId);
    }


    /**
     * method return list of bookings for concrete room for specified dates if they are exist
     * @param booking - object Booking that are contains concrete room and specified dates
     * @return - List of Bookings
     */
    @Override
    public List<Booking> getBookingOfRoomForBookingDate(Booking booking) {

        List<Booking> bookings = bookingRepository.getBookingByRoom(booking.getRoom().getId());
        long lookingStartDate = booking.getStartDate().getTime();
        long lookingEndDate = booking.getStartDate().getTime();
        return bookings.stream()
                .filter(b -> (lookingStartDate <= b.getEndDate().getTime() && b.getStartDate().getTime() <= lookingEndDate))
                .collect(Collectors.toList());
    }

    @Override
    public double getUserBookingPrice(long userId) {
        List<Booking> bookings = bookingRepository.getBookingByUser(userId);

        return bookings.stream()
                .map(b -> {
                    long numDays = ChronoUnit.DAYS.between(b.getStartDate().toLocalDate(), b.getEndDate().toLocalDate());
                    return numDays * b.getRoom().getPrice() + b.getRoom().getServices().stream().map(s -> s.getPrice() * numDays).reduce(0.0, (n, m) -> n + m);
                })
                .reduce(0.0, (i, j) -> i + j);
    }
}
