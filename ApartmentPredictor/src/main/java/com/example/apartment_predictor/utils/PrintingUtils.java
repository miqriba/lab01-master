package com.example.apartment_predictor.utils;

import com.example.apartment_predictor.model.Apartment;
import com.example.apartment_predictor.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintingUtils {
    @Autowired
    private ApartmentRepository apartmentRepository;

    public static void printList(List list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    public static void printList(List list, String title) {
        System.out.println("\n=== " + title + " ===");
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    public static void printApartments(CrudRepository apartmentRepository) {
        // Display all apartments in the database
        int index = 0;
        System.out.println("\n=== Apartments in the Database ===");
        for (Object apartment : apartmentRepository.findAll()) {
            index++;
            System.out.println("#" + index);
            System.out.println(apartment);
        }
    }

    public static void printApartmentsList(Iterable<Apartment> apartments) {
        // Display all apartments in the database
        int index = 0;
        System.out.println("\n=== Apartments in the Database ===");
        for (Apartment apartment : apartments) {
            index++;
            System.out.println("#" + index);
            System.out.println(apartment);
        }
    }

    public static void printObjectsList(Iterable<?> list) {
        // Display all apartments in the database
        int index = 0;
        System.out.println("\n=== Apartments in the Database ===");
        for (Object obj : list) {
            index++;
            System.out.println("#" + index);
            System.out.println(obj);
        }
    }

   public void printApartmentsByRepoInstance() {
        // Display all apartments in the database
        int index = 0;
        System.out.println("\n=== Apartments in the Database ===");
        for (Apartment apartment : apartmentRepository.findAll()) {
            index++;
            System.out.println("#" + index);
            System.out.println(apartment);
        }
    }

   /*public static void printApartmentsByRepo(){
    // Display all apartments in the database
    int index = 0;
    System.out.println("\n=== Apartments in the Database ===");
    for (Apartment apartment : apartmentRepository.findAll()) {
        index++;
        System.out.println("#" + index);
        System.out.println(apartment);
        int nil = 10;
    }
   }*/

}
