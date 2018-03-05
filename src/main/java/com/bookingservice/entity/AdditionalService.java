package com.bookingservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ADDITIONAL_SERVICE")
public class AdditionalService extends BaseEntity{

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;

    public AdditionalService() {
    }

    public AdditionalService(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
