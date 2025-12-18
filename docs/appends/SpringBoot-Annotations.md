# Spring Boot Testing Annotations

## Core Annotations

# 

**@RestController** combines `@Controller` and `@ResponseBody` annotations. It marks a class as a web controller where every method returns data directly to the HTTP response body (typically JSON/XML) rather than rendering views. Eliminates need for `@ResponseBody` on individual methods. Essential for building RESTful web services and APIs in Spring Boot applications.

**@Entity** - Marks a class as a JPA entity that maps to a database table. Each instance represents a row in the table. Used with `@Id` to define the primary key field.

**@Id** - Designates the primary key field in an entity class. Often paired with `@GeneratedValue` for auto-generated keys. Essential for JPA entity identification and database operations.

**@Repository** - Stereotypes a class as a Data Access Object (DAO). Provides exception translation from database-specific exceptions to Spring's DataAccessException hierarchy. Enables component scanning and dependency injection.

**@Service** - Marks a class as a service layer component containing business logic. Separates business operations from controllers and repositories. Makes the class eligible for dependency injection and component scanning.

**@Autowired** - Enables automatic dependency injection by Spring's IoC container. Can be applied to constructors, fields, or setter methods. Spring automatically resolves and injects matching bean dependencies at runtime.

### Architecture Pattern

These annotations work together in Spring's layered architecture:

- **Controller Layer** (`@RestControler`): Handles HTTP requests
- **Service Layer** (`@Service`): Contains business logic
- **Repository Layer** (`@Repository`): Manages data access
- **Entity Layer** (`@Entity`): Represents data models

## Best Practices

- Use constructor injection with `@Autowired` for better testability
- Keep entities focused on data representation
- Implement business logic in service classes
- Use repository interfaces extending JpaRepository for CRUD operations

> This annotation-driven approach promotes loose coupling, testability, and clean separation of concerns in Spring Boot applications.

## Test jUnit

## `@SpringBootTest`

The `@SpringBootTest` annotation is the primary testing annotation for Spring Boot applications:

- **Purpose**: Creates a full Spring application context for integration testing
- **Behavior**: Loads the entire Spring Boot application with all beans, configurations, and auto-configurations
- **Use Case**: Integration tests that need the complete application context
- **Default**: Runs on a random port and doesn't start an embedded web server unless specified

### Configuration Options:

```java
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = ApartmentPredictorApplication.class,
    properties = {"spring.profiles.active=test"}
)
```

## `@Test`

- **Framework**: JUnit 5 (Jupiter) annotation
- **Purpose**: Marks a method as a test method
- **Execution**: Method will be executed by the test runner

### **Unit Testing Annotations**

- **`@WebMvcTest`**: Tests only the web layer (controllers) without loading the full context
- **`@DataJpaTest`**: Tests JPA repositories with in-memory database
- **`@JsonTest`**: Tests JSON serialization/deserialization
- **`@RestClientTest`**: Tests REST clients

### **Integration Testing Annotations**

- **`@TestPropertySource`**: Overrides application properties for tests
- **`@ActiveProfiles`**: Activates specific Spring profiles during testing
- **`@DirtiesContext`**: Marks context as dirty, forcing reload between tests

### **Mock and Dependency Injection**

- **`@MockBean`**: Creates mock beans in the Spring context
- **`@SpyBean`**: Creates spy beans (partial mocks)
- **`@Autowired`**: Injects dependencies in test classes

### **Database Testing**

- **`@Transactional`**: Wraps test methods in transactions (usually rolled back)
- **`@Rollback`**: Controls transaction rollback behavior
- **`@Sql`**: Executes SQL scripts before/after test methods

# 
