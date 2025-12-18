# testReviewsInsert

Apartmentpredictor v1.0



```java
@Test
    void testReviewsInsert() {


        Review review1 = new Review();
        // after constructor is called
        // id is generated automatically bY UUID
        review1.setContent("This apartment exceeded my expectations. The location is perfect and the amenities are top-notch. Highly recommended for anyone looking for a comfortable stay.\t");
        review1.setRating(5);
        review1.setTitle("Nice Apartment in Fifth Avenue");
        review1.setReviewDate(LocalDate.now());
        // save review object to H2 test local-database
        // JPA repository SAVES it USING the method SAVE from crudRepository
        // reviewRepository uses autowired dependency injection
        // ReviewRepository is an interface AND extends CrudRepository
        reviewRepository.save(review1);


        System.out.println("Review saved: " + review1);

        // Display all reviews in the database
        int index = 0;
        System.out.println("\n=== Reviews in the Database ===");
        for (Review review : reviewRepository.findAll()) {
            index++;
            System.out.println("#" + index);
            System.out.println(review);
        }


    }
```
