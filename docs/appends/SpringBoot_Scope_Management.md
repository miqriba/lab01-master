# Scope Management in Java SE vs Spring Boot

## Overview

> This document analyzes the `ApartmentPredictorApplicationTests.java` file and demonstrates the <mark>fundamental differences in scope management </mark>between `Java SE` and `Spring Boot` applications, particularly focusing on **static** methods vs **dependency injection.**

## Test File Structure

Class Declaration

```java
@SpringBootTest
class ApartmentPredictorApplicationTests {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ApartmentRepository apartmentRepository;
    @Autowired
    PrintingUtils printingUtils;
}
```

The test class uses Spring Boot's `@SpringBootTest` annotation to create a full application context for integration testing.

## Test Method Analysis: `testApartmentsInsert()`

### 1. Entity Creation and Persistence

```java
Apartment apartment1 = new Apartment();
apartment1.setArea(5);
apartment1.setAirconditioning("yes");
apartmentRepository.save(apartment1);
```

### 2. PrintingUtils Method Calls Analysis

The test demonstrates **six different approaches** to calling printing methods, showcasing the scope management differences:

#### **Static Method Calls (Java SE Style)**

```java
// Generic List - Static methods with parameters
PrintingUtils.printList(List.copyOf((Collection<Apartment>) apartmentRepository.findAll()));
PrintingUtils.printList(List.copyOf((Collection<Apartment>) apartmentRepository.findAll()), "Using printList with title");

// Repository-based static methods
PrintingUtils.printApartments(apartmentRepository);
PrintingUtils.printApartmentsList(apartmentRepository.findAll());
PrintingUtils.printObjectsList(apartmentRepository.findAll());
```

#### **Instance Method Call (Spring Boot Style)**

```java
// Using injected instance with access to @Autowired dependencies
printingUtils.printApartmentsByRepoInstance();
```

### 3. JPA Relationship Management

```java
Review review1 = new Review();
// ... set properties
review1.setApartment(apartment1);
reviewRepository.save(review1);

// Bidirectional relationship management
apartment1.getReviews().add(review1);
apartmentRepository.save(apartment1);
```

## Scope Management: Java SE vs Spring Boot

### **Java SE Approach: Static Methods**

Characteristics:

- **Visibility**: Uses `static` modifier for class-level access
- **Memory**: Loaded at class loading time
- **Dependencies**: Must receive all dependencies as parameters
- **Instantiation**: No object creation required

#### **Example from PrintingUtils:**

```java
public static void printList(List list) {
    for (Object obj : list) {
        System.out.println(obj);
    }
}

public static void printApartments(CrudRepository apartmentRepository) {
    // Must pass repository as parameter
    for (Object apartment : apartmentRepository.findAll()) {
        System.out.println(apartment);
    }
}
```

**Advantages**:

- ✅ No object <mark>instantiation</mark> overhead
- ✅ Simple to call: `ClassName.methodName()`
- ✅ Memory efficient for utility methods

**Disadvantages**:

- ❌ Cannot access instance variables
- ❌ Must pass all dependencies as parameters
- ❌ No dependency injection support
- ❌ Harder to test and mock

### **Spring Boot Approach: Dependency Injection**

Characteristics:

- **Visibility**: Uses `@Component` and `@Autowired` annotations
- **Memory**: Managed by Spring IoC container
- **Dependencies**: Automatically injected by Spring
- **Instantiation**: Spring creates and manages instances

#### **Example from PrintingUtils:**

```java
@Component
public class PrintingUtils {
    @Autowired
    private ApartmentRepository apartmentRepository;

    public void printApartmentsByRepoInstance() {
        // Direct access to injected dependency
        for (Apartment apartment : apartmentRepository.findAll()) {
            System.out.println(apartment);
        }
    }
}
```

#### **Usage in Test:**

```java
@Autowired
PrintingUtils printingUtils;

// Call instance method with access to injected dependencies
printingUtils.printApartmentsByRepoInstance();
```

 **Advantages:**

- ✅ Automatic dependency injection
- ✅ Better testability with mocking
- ✅ Cleaner method signatures
- ✅ Spring lifecycle management
- ✅ Configuration flexibility

**Disadvantages:**

- ❌ Requires Spring context
- ❌ Slightly more memory overhead
- ❌ More complex setup

## Commented Code Analysis

### **Failed Static Approach:**

```java
//PrintingUtils.printApartmentsByRepo();
```

This is commented out because **static methods cannot access instance variables** (`@Autowired` fields).

### **Manual Instantiation Attempt:**

```java
//PrintingUtils utils = new PrintingUtils();
//utils.printApartmentsByRepoInstance();
```

This fails because manual instantiation bypasses Spring's dependency injection - the `@Autowired` fields remain `null`.

## Best Practices Summary

**Use Static Methods When:**

- Creating utility functions that don't need external dependencies
- Working with pure functions (input → output)
- Building helper methods for data transformation
- Working in non-Spring environments

**Use Dependency Injection When:**

- Need access to Spring-managed beans (repositories, services)
- Building business logic that requires multiple dependencies
- Creating testable components
- Working within Spring Boot applications

## Key Takeaways

1. **Static methods** require all dependencies as parameters but offer simplicity
2. **Instance methods with DI** provide cleaner code but require Spring context
3. **@Autowired fields** are only populated when Spring creates the instance
4. **Manual instantiation** bypasses Spring's dependency injection mechanism
5. **Test environment** demonstrates both approaches working together effectively
