package com.example.apartment_predictor.model;

import jakarta.persistence.Entity;

@Entity
public class Apartment extends ResidentialProperty{

    private Long price;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer stories;
    private String mainroad;
    private String guestroom;
    private String basement;
    private String hotwaterheating;
    private String airconditioning;
    private Integer parking;
    private String prefarea;
    private String furnishingstatus;

    private int floorLevel;
    private boolean hasBalcony;

    public Apartment() {}

    public Apartment(double area, int locationRating, String address, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden, Long price, Integer bedrooms, Integer bathrooms, Integer stories, String mainroad, String guestroom, String basement, String hotwaterheating, String airconditioning, Integer parking, String prefarea, String furnishingstatus, int floorLevel, boolean hasBalcony) {
        super(area, locationRating, address, numberOfBedrooms, numberOfBathrooms, hasGarden);
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.stories = stories;
        this.mainroad = mainroad;
        this.guestroom = guestroom;
        this.basement = basement;
        this.hotwaterheating = hotwaterheating;
        this.airconditioning = airconditioning;
        this.parking = parking;
        this.prefarea = prefarea;
        this.furnishingstatus = furnishingstatus;
        this.floorLevel = floorLevel;
        this.hasBalcony = hasBalcony;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getStories() {
        return stories;
    }

    public void setStories(Integer stories) {
        this.stories = stories;
    }

    public String getMainroad() {
        return mainroad;
    }

    public void setMainroad(String mainroad) {
        this.mainroad = mainroad;
    }

    public String getGuestroom() {
        return guestroom;
    }

    public void setGuestroom(String guestroom) {
        this.guestroom = guestroom;
    }

    public String getBasement() {
        return basement;
    }

    public void setBasement(String basement) {
        this.basement = basement;
    }

    public String getHotwaterheating() {
        return hotwaterheating;
    }

    public void setHotwaterheating(String hotwaterheating) {
        this.hotwaterheating = hotwaterheating;
    }

    public String getAirconditioning() {
        return airconditioning;
    }

    public void setAirconditioning(String airconditioning) {
        this.airconditioning = airconditioning;
    }

    public Integer getParking() {
        return parking;
    }

    public void setParking(Integer parking) {
        this.parking = parking;
    }

    public String getPrefarea() {
        return prefarea;
    }

    public void setPrefarea(String prefarea) {
        this.prefarea = prefarea;
    }

    public String getFurnishingstatus() {
        return furnishingstatus;
    }

    public void setFurnishingstatus(String furnishingstatus) {
        this.furnishingstatus = furnishingstatus;
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    @Override
    public double calculatePrice() {
        int numberOfRooms = numberOfBathrooms + numberOfBedrooms;
        double basePrice = area * 180 + (numberOfRooms * 15000);
        if (hasBalcony) basePrice += 10000;
        return basePrice * (1 +(locationRating * 0.6));
    }

    @Override
    public double calculateAnnualPropertyTax() {
        double price = calculatePrice();

        return price * 0.015 * (1 +(locationRating * 0.6));
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "price=" + price +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", stories=" + stories +
                ", mainroad='" + mainroad + '\'' +
                ", guestroom='" + guestroom + '\'' +
                ", basement='" + basement + '\'' +
                ", hotwaterheating='" + hotwaterheating + '\'' +
                ", airconditioning='" + airconditioning + '\'' +
                ", parking=" + parking +
                ", prefarea='" + prefarea + '\'' +
                ", furnishingstatus='" + furnishingstatus + '\'' +
                ", floorLevel=" + floorLevel +
                ", hasBalcony=" + hasBalcony +
                '}';
    }
}
