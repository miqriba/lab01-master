package com.example.apartment_predictor.model;

import jakarta.persistence.Entity;

@Entity
public abstract class ResidentialProperty extends Property{

    protected int numberOfBedrooms;
    protected int numberOfBathrooms;
    protected boolean hasGarden;

    public ResidentialProperty() {}

    public ResidentialProperty(double area, int locationRating, String address, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden) {
        super(area, locationRating, address);
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.hasGarden = hasGarden;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public boolean isHasGarden() {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    // Method that compares the number of rooms from a property to find out if it's suitable for a family.
    // For each room we can have 1-2 people.
    // for each bathroom we can have max 3 people.
    public boolean isSuitableForFamily(int familySize) {

        int maxPeopleForBedroom = 2;
        int maxPeopleForBathroom = 3;

        if ((double) familySize/numberOfBedrooms <= maxPeopleForBedroom && (double) familySize/numberOfBathrooms <= maxPeopleForBathroom) {
            return true;
        }
        else return false;
    }

    @Override
    public String toString() {
        return "ResidentialProperty{" +
                "numberOfBedrooms=" + numberOfBedrooms +
                ", numberOfBathrooms=" + numberOfBathrooms +
                ", hasGarden=" + hasGarden +
                '}';
    }
}
