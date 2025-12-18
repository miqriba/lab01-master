package com.example.apartment_predictor.model;

public abstract class SingleFamilyHome extends ResidentialProperty {

    protected double lotSize;
    protected boolean hasGarage;
    protected boolean hasPool;

    public SingleFamilyHome() {}

    public SingleFamilyHome(double area, int locationRating, String address, int numberOfBedrooms, int numberOfBathrooms, boolean hasGarden, double lotSize, boolean hasGarage, boolean hasPool) {
        super(area, locationRating, address, numberOfBedrooms, numberOfBathrooms, hasGarden);
        this.lotSize = lotSize;
        this.hasGarage = hasGarage;
        this.hasPool = hasPool;
    }

    public double getLotSize() {
        return lotSize;
    }

    public void setLotSize(double lotSize) {
        this.lotSize = lotSize;
    }

    public boolean isHasGarage() {
        return hasGarage;
    }

    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    @Override
    public String toString() {
        return "SingleFamilyHome{" +
                "lotSize=" + lotSize +
                ", hasGarage=" + hasGarage +
                ", hasPool=" + hasPool +
                '}';
    }
}
