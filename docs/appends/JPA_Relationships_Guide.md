# JPA Relationships Guide: Understanding @OneToMany and @ManyToOne

## Introduction

> When building applications with databases, we often need to represent relationships between different types of data. In our Apartment Predictor application, we have **Apartments** and **Reviews** - and these two entities have a natural relationship: one apartment can have multiple reviews, but each review belongs to only one apartment.

This is where JPA (Java Persistence API) relationships come into play. They help us <mark>map these real-world relationships into our database and Java code.</mark>

## The Real-World Scenario

Think about it like this:

- 🏠 **One Apartment** (e.g., "Luxury Apartment on 5th Avenue")
- 📝 **Multiple Reviews** for that apartment:
  - "Great location and amenities!" - ⭐⭐⭐⭐⭐
  - "Clean and comfortable" - ⭐⭐⭐⭐
  - "Perfect for business trips" - ⭐⭐⭐⭐⭐

## Why Do We Need JPA Relationships?

### Without Relationships (The Problem)

```java
// ❌ BAD: No relationship mapping
@Entity
public class Apartment {
    @Id
    private String id;
    private String address;
    // No connection to reviews!
}

@Entity  
public class Review {
    @Id
    private String id;
    private String content;
    private int rating;
    // No connection to apartment!
}
```

**Problems with this approach:**

1. **No Data Integrity**: Nothing prevents a review from referencing a non-existent apartment
2. **Manual Queries**: You'd have to manually write complex SQL to find all reviews for an apartment
3. **Data Inconsistency**: If an apartment is deleted, orphaned reviews remain
4. **Poor Performance**: Multiple database queries needed to get related data

### With Relationships (The Solution)

```java
// ✅ GOOD: Proper relationship mapping
@Entity
public class Apartment {
    @Id
    private String id;
    private String address;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}

@Entity
public class Review {
    @Id
    private String id;
    private String content;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
```

## How JPA Relationships Work

### @OneToMany Annotation (Apartment Side)

```java
@OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<Review> reviews = new ArrayList<>();
```

**What this means:**

- **@OneToMany**: "This apartment can have many reviews"
- **mappedBy = "apartment"**: "The relationship is controlled by the 'apartment' field in the Review entity"
- **cascade = CascadeType.ALL**: "When I save/delete an apartment, also save/delete its reviews"
- **fetch = FetchType.LAZY**: "Don't load reviews until I actually need them (performance optimization)"

### @ManyToOne Annotation (Review Side)

```java
@ManyToOne
@JoinColumn(name = "apartment_id")
private Apartment apartment;
```

**What this means:**

- **@ManyToOne**: "Many reviews can belong to one apartment"
- **@JoinColumn(name = "apartment_id")**: "Store the apartment's ID in a column called 'apartment_id'"

## Database Structure Created

When you use these annotations, JPA automatically creates this database structure:

### Apartment Table

```sql
CREATE TABLE apartment (
    id VARCHAR(255) PRIMARY KEY,
    address VARCHAR(255),
    price BIGINT,
    area INTEGER,
    -- other apartment fields...
);
```

### Review Table

```sql
CREATE TABLE review (
    id VARCHAR(255) PRIMARY KEY,
    content TEXT,
    rating INTEGER,
    review_date DATE,
    apartment_id VARCHAR(255),  -- 👈 Foreign key to apartment table
    FOREIGN KEY (apartment_id) REFERENCES apartment(id)
);
```

## Practical Example: How It Works in Code

### 1. Creating an Apartment with Reviews

```java
@Test
void createApartmentWithReviews() {
    // Create apartment
    Apartment apartment = new Apartment();
    apartment.setAddress("123 Main Street");
    apartment.setPrice(2500L);

    // Create reviews
    Review review1 = new Review();
    review1.setContent("Amazing apartment with great amenities!");
    review1.setRating(5);
    review1.setReviewDate(LocalDate.now());

    Review review2 = new Review();
    review2.setContent("Clean and comfortable, highly recommended.");
    review2.setRating(4);
    review2.setReviewDate(LocalDate.now());

    // 🔗 Establish the bidirectional relationship
    review1.setApartment(apartment);  // Review → Apartment
    review2.setApartment(apartment);  // Review → Apartment

    apartment.getReviews().add(review1);  // Apartment → Review
    apartment.getReviews().add(review2);  // Apartment → Review

    // Save (cascade will save reviews automatically)
    apartmentRepository.save(apartment);
}
```

### 2. Querying Related Data

```java
@Test
void findApartmentWithReviews() {
    // Find apartment by ID
    Apartment apartment = apartmentRepository.findById("some-id").orElse(null);

    // Access reviews (JPA handles the database join automatically!)
    List<Review> reviews = apartment.getReviews();

    System.out.println("Apartment: " + apartment.getAddress());
    System.out.println("Number of reviews: " + reviews.size());

    for (Review review : reviews) {
        System.out.println("⭐".repeat(review.getRating()) + " - " + review.getContent());
    }
}
```

### 3. Finding Reviews for a Specific Apartment

```java
@Test
void findReviewsByApartment() {
    // You can also query from the Review side
    List<Review> reviews = reviewRepository.findByApartmentId("apartment-id");

    for (Review review : reviews) {
        System.out.println("Review: " + review.getContent());
        System.out.println("For apartment: " + review.getApartment().getAddress());
    }
}
```

## What Problems Do JPA Relationships Solve?

### 1. **Data Integrity**

- Foreign key constraints ensure reviews can't reference non-existent apartments
- Cascade operations keep data consistent

### 2. **Automatic Query Generation**

- JPA generates efficient SQL joins automatically
- No need to write complex manual queries

### 3. **Object-Oriented Navigation**

```java
// Navigate from apartment to reviews
apartment.getReviews().forEach(review -> 
    System.out.println(review.getContent()));

// Navigate from review to apartment
String apartmentAddress = review.getApartment().getAddress();
```

### 4. **Performance Optimization**

- Lazy loading: Reviews are only loaded when accessed
- Efficient batch operations with cascade

### 5. **Simplified CRUD Operations**

```java
// Delete apartment and all its reviews in one operation
apartmentRepository.delete(apartment);  // Reviews deleted automatically due to cascade
```

## Common Pitfalls and Best Practices

### ❌ Don't Forget Bidirectional Relationships

```java
// BAD: Only setting one side
apartment.getReviews().add(review);  // ❌ Review doesn't know its apartment

// GOOD: Set both sides
review.setApartment(apartment);      // ✅ Review → Apartment
apartment.getReviews().add(review);  // ✅ Apartment → Review
```

### ❌ Don't Use Eager Loading for Large Collections

```java
// BAD: Loads all reviews immediately (performance issue)
@OneToMany(fetch = FetchType.EAGER)
private List<Review> reviews;

// GOOD: Load reviews only when needed
@OneToMany(fetch = FetchType.LAZY)
private List<Review> reviews;
```

### ✅ Use Helper Methods for Relationship Management

```java
public class Apartment {
    // Helper method to maintain bidirectional relationship
    public void addReview(Review review) {
        reviews.add(review);
        review.setApartment(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setApartment(null);
    }
}
```

## Summary

JPA relationships like `@OneToMany` and `@ManyToOne` are essential because they:

1. **Model Real-World Relationships**: Apartments naturally have multiple reviews
2. **Ensure Data Integrity**: Foreign key constraints prevent orphaned data
3. **Simplify Code**: Navigate between objects naturally (`apartment.getReviews()`)
4. **Optimize Performance**: JPA generates efficient SQL queries automatically
5. **Maintain Consistency**: Cascade operations keep related data synchronized

In our Apartment Predictor application, this means we can easily:

- Find all reviews for a specific apartment
- Calculate average ratings per apartment
- Delete apartments and their reviews safely
- Navigate from reviews back to apartment details

> Without these relationships, we'd be stuck with manual SQL queries, data integrity issues, and much more complex code!
