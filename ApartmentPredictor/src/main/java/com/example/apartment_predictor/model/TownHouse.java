package com.example.apartment_predictor.model;

public class TownHouse extends SingleFamilyHome{

    private boolean hasHomeownerAssociation;
    private double hoaMonthlyFee;

    public TownHouse() { }

    public TownHouse(double area, int locationRating, String address, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden, double lotSize, boolean hasGarage, boolean hasPool, boolean hasHomeownerAssociation, double hoaMonthlyFee) {
        super(area, locationRating, address, numberOfBedrooms, numberOfBathrooms, hasGarden, lotSize, hasGarage, hasPool);
        this.hasHomeownerAssociation = hasHomeownerAssociation;
        this.hoaMonthlyFee = hoaMonthlyFee;
    }

    public boolean isHasHomeownerAssociation() {
        return hasHomeownerAssociation;
    }

    public void setHasHomeownerAssociation(boolean hasHomeownerAssociation) {
        this.hasHomeownerAssociation = hasHomeownerAssociation;
    }

    public double getHoaMonthlyFee() {
        return hoaMonthlyFee;
    }

    public void setHoaMonthlyFee(double hoaMonthlyFee) {
        this.hoaMonthlyFee = hoaMonthlyFee;
    }

    // completely overrides ResidentialProperty method by modifying all conditions
    @Override
    public boolean isSuitableForFamily(int familySize) {
        if (numberOfBedrooms >= familySize && numberOfBathrooms *2 >= familySize) {
            return true;
        } else return false;
    }
}
