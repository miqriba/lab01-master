package com.example.apartment_predictor;

import com.example.apartment_predictor.model.Apartment;
import com.example.apartment_predictor.model.Duplex;
import com.example.apartment_predictor.model.House;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

// Albert's tests

@SpringBootTest
public class ApartmentInherenceTest {


    /*@Test
    void testInherence() {

        /*Apartment apartment = new Apartment();
        //apartment.setId("APT-001-K");
        apartment.setBathrooms(2);
        apartment.setBedrooms(3);
        apartment.setArea(100);
        apartment.setStories(2);
        apartment.setBasement("Yes");
        apartment.setMainroad("Yes");
        apartment.setPrefarea("Yes");
        apartment.setGuestroom("Yes");
        apartment.setParking(2);

        apartment.setPrice(10000L);

        System.out.println(apartment);


        House house = new House();
        //house.setId("HOU-001-K");
        house.setGarden("Yes");
        house.setGarageQty(2);
        house.setRoofType("Tile");

        house.setAirconditioning("Yes");
        house.setArea(100);

        System.out.println(house);

        Duplex duplex = new Duplex();
        //duplex.setId("DUP-001-K");
        duplex.setBalcony("Yes");
        duplex.setElevator("Yes");

        duplex.setBathrooms(2);
        duplex.setBedrooms(3);
        duplex.setArea(100);
        duplex.setStories(2);
        duplex.setBasement("Yes");
        duplex.setMainroad("Yes");
        duplex.setPrefarea("Yes");
        duplex.setGuestroom("Yes");
        duplex.setParking(2);

        System.out.println(duplex);
    }

    @Test
    void testInherenceList() {

        List<Apartment> apartments = new ArrayList<>();

        Apartment apartment = new Apartment();
        //apartment.setId("APT-001-K");
        apartment.setBathrooms(2);
        apartment.setBedrooms(3);
        apartment.setArea(100);
        apartment.setStories(2);
        apartment.setBasement("Yes");
        apartment.setMainroad("Yes");
        apartment.setPrefarea("Yes");
        apartment.setGuestroom("Yes");
        apartment.setParking(2);

        apartment.setPrice(10000L);

        System.out.println(apartment);


        /*Apartment house = new House();
        //house.setId("HOU-001-K");
        ((House) house).setGarden("Yes");
        ((House) house).setGarageQty(2);
        ((House) house).setRoofType("Tile");

        house.setAirconditioning("Yes");
        house.setArea(100);

        System.out.println(house);

        Apartment duplex = new Duplex();
        //duplex.setId("DUP-001-K");
        ((Duplex) duplex).setBalcony("Yes");
        ((Duplex) duplex).setElevator("Yes");

        duplex.setBathrooms(2);
        duplex.setBedrooms(3);
        duplex.setArea(100);
        duplex.setStories(2);
        duplex.setBasement("Yes");
        duplex.setMainroad("Yes");
        duplex.setPrefarea("Yes");
        duplex.setGuestroom("Yes");
        duplex.setParking(2);

        System.out.println(duplex);

        apartments.add(apartment);
        apartments.add(house);
        apartments.add(duplex);

        System.out.println("\n=== Apartments in the List ===");
        apartments.forEach(System.out::println);


    }*/
}
