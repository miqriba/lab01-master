package com.example.apartment_predictor.model;

public abstract class ResidentialProperty extends Property {

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

    // LAB01 base isSuitableForFamily method in father abstract class that will be inherited by children classes
    public boolean isSuitableForFamily (int familySize) {
        if (numberOfBedrooms *2 >= familySize && numberOfBathrooms * 3 >= familySize) {
            return true;
        } else return false;
    }
}
