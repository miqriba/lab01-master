package com.example.apartment_predictor.PropertyTests;

import com.example.apartment_predictor.model.Apartment;
import com.example.apartment_predictor.model.Duplex;
import com.example.apartment_predictor.model.House;
import com.example.apartment_predictor.model.Property;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// LAB01 Nil-Eric-Miquel's tests

@SpringBootTest
public class isSuitableForFamilyTest {

    @Test
    void isSuitableForFamily () {
        Property house1 = new House (
                130.00, //area
                3, //locationRating
                "C/Comte Sert n1", //Address
                5, //numberOfBedrooms
                2, //numberOfBathrooms
                true, //hasGarden
                1500, //lotSize
                true, //hasGarage
                true, //hasPool
                true //hasBasement
        );

        Property house2 = new House (
                130.00, //area
                3, //locationRating
                "C/Comte Sert n1", //Address
                2, //numberOfBedrooms
                1, //numberOfBathrooms
                true, //hasGarden
                1500, //lotSize
                true, //hasGarage
                false, //hasPool
                false //hasBasement
        );

        Property duplex1 = new Duplex(
                130.00, //area
                3, //locationRating
                "C/Comte Sert n1", //Address
                5, //numberOfBedrooms
                2, //numberOfBathrooms
                true, //hasGarden
                1500, //lotSize
                true, //hasGarage
                true, //hasPool
                true //hasSeparatedUtilities
        );
    }

}
