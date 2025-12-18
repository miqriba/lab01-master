package com.example.apartment_predictor.model;

import jakarta.persistence.Entity;

@Entity
public class House extends SingleFamilyHome{

    private boolean hasBasement;

    public House() {}

    public House(double area, int locationRating, String address, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden, double lotSize, boolean hasGarage, boolean hasPool, boolean hasBasement) {
        super(area, locationRating, address, numberOfBedrooms, numberOfBathrooms, hasGarden, lotSize, hasGarage, hasPool);
        this.hasBasement = hasBasement;
    }

    public boolean isHasBasement() {
        return hasBasement;
    }

    public void setHasBasement(boolean hasBasement) {
        this.hasBasement = hasBasement;
    }

    // For the house version of this Method we changed some conditions.
    // For each bathroom we allow max 2 people instead of 3.
    // In addition, our number of rooms increase in 1 if our house has a basement.
    @Override
    public boolean isSuitableForFamily(int familySize) {

        int maxPeopleForBedroom = 2;
        int maxPeopleForBathroom = 2;

        if (hasBasement) numberOfBedrooms++;

        if ((double) familySize/numberOfBedrooms <= maxPeopleForBedroom && (double) familySize/numberOfBathrooms <= maxPeopleForBathroom) {
            return true;
        }
        else return false;
    }

    @Override
    public String toString() {
        return "House{" +
                "hasBasement=" + hasBasement +
                '}';
    }
}
