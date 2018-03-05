package com.bookingservice.entity;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.sql.Date;

@Entity
@Table(name = "BOOKING")
public class Booking extends BaseEntity {

    @NotNull
    @Column(name="START_DATE")
    private Date startDate;

    @AssertTrue(message="Your Date is not valid")
    private boolean isDateValid() {
        long start = startDate.getTime();
        long end = endDate.getTime();
        long current = Instant.now().toEpochMilli();
        return  start < end && start > current;
    }

    @Column(name = "END_DATE")
    private Date endDate;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;



    public Booking() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;

        Booking booking = (Booking) o;

        if (startDate != null ? !startDate.equals(booking.startDate) : booking.startDate != null) return false;
        if (endDate != null ? !endDate.equals(booking.endDate) : booking.endDate != null) return false;
        return room != null ? room.equals(booking.room) : booking.room == null;
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        return result;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
