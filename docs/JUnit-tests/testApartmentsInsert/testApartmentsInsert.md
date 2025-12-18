# testApartmentsInsert()

ApartmentPredictor v1.0

```java
    
    @Test
    void testApartmentsInsert() {

        Apartment apartment1 = new Apartment();
        apartment1.setArea(5);
        apartment1.setAirconditioning("yes");
        // ....
        apartmentRepository.save(apartment1);
        System.out.println("Apartment saved: " + apartment1);

        // Display all apartments in the database
        int index = 0;
        System.out.println("\n=== Apartments in the Database ===");
        for (Apartment apartment : apartmentRepository.findAll()) {
            index++;
            System.out.println("#" + index);
            System.out.println(apartment);
        }

        Review review1 = new Review();
        review1.setContent("This apartment exceeded my expectations. The location is perfect and the amenities are top-notch. Highly recommended for anyone looking for a comfortable stay.");
        review1.setRating(5);
        review1.setTitle("Nice Apartment in Fifth Avenue");
        review1.setReviewDate(LocalDate.now());
        review1.setApartment(apartment1);

        reviewRepository.save(review1);

        apartment1.getReviews().add(review1);

        apartmentRepository.save(apartment1);

        // Display all apartments in the database
        index = 0;
        System.out.println("\n=== Apartments in the Database ===");
        for (Apartment apartment : apartmentRepository.findAll()) {
            index++;
            System.out.println("#" + index);
            System.out.println(apartment);
        }
    }
    
    
    
    
```
