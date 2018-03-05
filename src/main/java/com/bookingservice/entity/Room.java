package com.bookingservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROOM")
public class Room extends BaseEntity {

    @Column(name = "NUMBER")
    private int number;

    @Column(name = "PRICE")
    private double price;

    @ManyToOne
    @JoinColumn(name = "ROOM_CATEGORY_ID")
    private RoomCategory category;

    @ManyToOne
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;


    @JsonIgnore
    @OneToMany
    private List<Booking> bookings;

    @ManyToMany
    @JoinTable(name = "ROOM_ADDITIONAL_SERVICE",
            joinColumns = @JoinColumn(name = "ROOM_ID"),
            inverseJoinColumns = @JoinColumn(name = "ADDITIONAL_SERVICE_ID"))
    private List<AdditionalService> services;

    public Room() { }

    public Room(int number, double price, RoomCategory category, Hotel hotel) {
        this.number = number;
        this.price = price;
        this.category = category;
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        if (number != room.number) return false;
        return hotel != null ? hotel.equals(room.hotel) : room.hotel == null;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        return result;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<AdditionalService> getServices() {
        return services;
    }

    public void setServices(List<AdditionalService> services) {
        this.services = services;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
