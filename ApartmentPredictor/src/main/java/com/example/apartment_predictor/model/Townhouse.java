package com.example.apartment_predictor.model;

import jakarta.persistence.Entity;

@Entity
public class Townhouse extends SingleFamilyHome{

    private boolean hasHomeownersAssociation;
    private double hoaMonthlyFee;

    public Townhouse() {}

    public Townhouse(double area, int locationRating, String address, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden, double lotSize, boolean hasGarage, boolean hasPool, boolean hasHomeownersAssociation, double hoaMonthlyFee) {
        super(area, locationRating, address, numberOfBedrooms, numberOfBathrooms, hasGarden, lotSize, hasGarage, hasPool);
        this.hasHomeownersAssociation = hasHomeownersAssociation;
        this.hoaMonthlyFee = hoaMonthlyFee;
    }

    public boolean isHasHomeownersAssociation() {
        return hasHomeownersAssociation;
    }

    public void setHasHomeownersAssociation(boolean hasHomeownersAssociation) {
        this.hasHomeownersAssociation = hasHomeownersAssociation;
    }

    public double getHoaMonthlyFee() {
        return hoaMonthlyFee;
    }

    public void setHoaMonthlyFee(double hoaMonthlyFee) {
        this.hoaMonthlyFee = hoaMonthlyFee;
    }

    // Similar to the House method, in this case we don't have the increase of rooms.
    // It also changes the conditions of max numbers of rooms.
    @Override
    public boolean isSuitableForFamily(int familySize) {

        int maxPeopleForBedroom = 2;
        int maxPeopleForBathroom = 2;

        if ((double) familySize/numberOfBedrooms <= maxPeopleForBedroom && (double) familySize/numberOfBathrooms <= maxPeopleForBathroom) {
            return true;
        }
        else return false;
    }

    @Override
    public String toString() {
        return "Townhouse{" +
                "hasHomeownersAssociation=" + hasHomeownersAssociation +
                ", hoaMonthlyFee=" + hoaMonthlyFee +
                '}';
    }
}
