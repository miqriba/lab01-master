# testApartmentsInsert

- [GitHub - AlbertProfe/ApartmentPredictor at 7382b921307e52052ca4c4bdce8ab98eabdf8d83](https://github.com/AlbertProfe/ApartmentPredictor/tree/7382b921307e52052ca4c4bdce8ab98eabdf8d83)

- https://github.com/AlbertProfe/ApartmentPredictor/blob/7382b921307e52052ca4c4bdce8ab98eabdf8d83/ApartmentPredictor/src/main/java/com/example/apartment_predictor/utils/PrintingUtils.java





```java
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ApartmentRepository apartmentRepository;
    @Autowired
    PrintingUtils printingUtils;

    @Test
    void testApartmentsInsert() {

        Apartment apartment1 = new Apartment();
        apartment1.setArea(5);
        apartment1.setAirconditioning("yes");
        // ....
        apartmentRepository.save(apartment1);
        System.out.println("Apartment saved: " + apartment1);

       /* // Display all apartments in the database
        int index = 0;
        System.out.println("\n=== Apartments in the Database ===");
        for (Apartment apartment : apartmentRepository.findAll()) {
            index++;
            System.out.println("#" + index);
            System.out.println(apartment);
        }*/

        // Test all PrintingUtils methods
        // Generic List
        PrintingUtils.printList(List.copyOf((Collection<Apartment>) apartmentRepository.findAll()));
        PrintingUtils.printList(List.copyOf((Collection<Apartment>) apartmentRepository.findAll()), "Using printList with title");
        // Sending data by parameter, using CrudRepository
        PrintingUtils.printApartments(apartmentRepository);
        PrintingUtils.printApartmentsList(apartmentRepository.findAll());
        // Sending data by parameter, using Iterable and generic
        PrintingUtils.printObjectsList(apartmentRepository.findAll());
        // Calling it as an instance method, using Iterable and generic
        printingUtils.printApartmentsByRepoInstance();

        //PrintingUtils.printApartmentsByRepo();
        //PrintingUtils utils = new PrintingUtils();
        // Trying to call it as an instance method
        //utils.printApartmentsByRepoInstance();

        Review review1 = new Review();
        review1.setContent("This apartment exceeded my expectations. The location is perfect and the amenities are top-notch. Highly recommended for anyone looking for a comfortable stay.");
        review1.setRating(5);
        review1.setTitle("Nice Apartment in Fifth Avenue");
        review1.setReviewDate(LocalDate.now());
        review1.setApartment(apartment1);

        reviewRepository.save(review1);

        apartment1.getReviews().add(review1);

        apartmentRepository.save(apartment1);

        /*// Display all apartments in the database
        index = 0;
        System.out.println("\n=== Apartments in the Database ===");
        for (Apartment apartment : apartmentRepository.findAll()) {
            index++;
            System.out.println("#" + index);
            System.out.println(apartment);
        }*/
    }
```
