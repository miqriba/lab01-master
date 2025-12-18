package com.example.apartment_predictor.model;


public abstract class Property {
    protected double area;
    protected int locationRating;
    protected String address;

    public Property() {}

    public Property(double area, int locationRating, String address) {
        this.area = area;
        this.locationRating = locationRating;
        this.address = address;
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

    //public abstract double calculatePrice();

}
