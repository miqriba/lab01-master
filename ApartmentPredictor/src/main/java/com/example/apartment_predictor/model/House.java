package com.example.apartment_predictor.model;

import java.util.UUID;

public class House extends SingleFamilyHome {

    private int garageQty;
    private String roofType;
    private String garden;
    private boolean hasBasement;

    public House() { }

    public House(double area, int locationRating, String address, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden, double lotSize, boolean hasGarage, boolean hasPool, boolean hasBasement) {
        super(area, locationRating, address, numberOfBedrooms, numberOfBathrooms, hasGarden, lotSize, hasGarage, hasPool);
        this.hasBasement = hasBasement;
    }

    public int getGarageQty() {
        return garageQty;
    }

    public void setGarageQty(int garageQty) {
        this.garageQty = garageQty;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    public boolean isHasBasement() {
        return hasBasement;
    }

    public void setHasBasement(boolean hasBasement) {
        this.hasBasement = hasBasement;
    }

    @Override
    public String toString() {
        return "House{" +
                "garageQty=" + garageQty +
                ", roofType='" + roofType + '\'' +
                ", garden='" + garden + '\'' +
                ", hasBasement=" + hasBasement +
                "} " + super.toString();
    }

    // completely overrides ResidentialProperty method by modifying all conditions
    @Override
    public boolean isSuitableForFamily(int familySize) {
        int rooms = numberOfBedrooms;
        if (hasBasement) {
            rooms++;
        }
        if (rooms >= familySize && numberOfBathrooms *2 >= familySize) {
            return true;
        } else return false;
    }
}
