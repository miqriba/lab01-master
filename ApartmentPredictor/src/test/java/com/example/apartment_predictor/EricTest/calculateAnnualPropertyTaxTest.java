package com.example.apartment_predictor.EricTest;

import com.example.apartment_predictor.model.Apartment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class calculateAnnualPropertyTaxTest {

    @Test
    void testCalculateAnnualPropertyTaxApartmentVariation() {

        Apartment apartment = new Apartment();
        apartment.setBedrooms(2);
        apartment.setBathrooms(1);
        apartment.setHasBalcony(true);
        apartment.setArea(120.00);
        apartment.setLocationRating(3);

        System.out.println(apartment.calculatePrice());
        System.out.println(apartment.calculateAnnualPropertyTax());
    }
}
