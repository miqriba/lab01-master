package com.example.apartment_predictor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Property {

    @Id
    protected String id;
    protected double area;
    protected int locationRating;
    protected String address;

    public Property() {
        this.id = UUID.randomUUID().toString();
    }

    public Property(double area, int locationRating, String address) {
        this();
        this.area = area;
        this.locationRating = locationRating;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getLocationRating() {
        return locationRating;
    }

    public void setLocationRating(int locationRating) {
        this.locationRating = locationRating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id='" + id + '\'' +
                ", area=" + area +
                ", locationRating=" + locationRating +
                ", address='" + address + '\'' +
                '}';
    }
}
