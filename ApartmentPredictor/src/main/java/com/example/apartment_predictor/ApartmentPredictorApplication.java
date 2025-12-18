package com.example.apartment_predictor;

import com.example.apartment_predictor.model.Apartment;
import com.example.apartment_predictor.model.Review;
import com.example.apartment_predictor.repository.ApartmentRepository;
import com.example.apartment_predictor.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ApartmentPredictorApplication {

    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private ReviewRepository reviewRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApartmentPredictorApplication.class, args);
	}
}


