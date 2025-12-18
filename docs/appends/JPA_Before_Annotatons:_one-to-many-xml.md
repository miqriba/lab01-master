# Before annotations: one to many .xml

> Before <mark>annotations</mark> were common, the `Apartment–Review` relationship was defined in `ORM XM`L (Hibernate `*.hbm.xml` or JPA `orm.xml`), and Spring only wired the `ORM laye`r (`SessionFactory`/`EntityManagerFactory`, **transactions**) via its own XML. 
> 
> The <mark>mapping</mark> itself did not live in Spring XML.

## Hibernate mapping XML example

For classic Hibernate (pre‑JPA style), you would have something like this:

- `Apartment.hbm.xml` would map the table and the one‑to‑many collection.
- `Review.hbm.xml` would map the table and the many‑to‑one back to `Apartment`.

Conceptually (simplified):

**Apartment.hbm.xml**

```xml
<class name="com.example.Apartment" table="apartment">
    <id name="id" column="id" type="string">
        <generator class="assigned"/>
    </id>

    <!-- other primitive fields -->

    <set name="reviews" inverse="true" cascade="all">
        <key column="apartment_fk"/>
        <one-to-many class="com.example.Review"/>
    </set>
</class>
```

**Review.hbm.xml**

```xml
<class name="com.example.Review" table="review">
    <id name="id" column="id" type="string">
        <generator class="assigned"/>
    </id>

    <!-- title, content, rating, reviewDate, ... -->

    <many-to-one name="apartment"
                 class="com.example.Apartment"
                 column="apartment_fk"
                 not-null="true"/>
</class>
```

> This yields exactly the same schema as your annotated example: a `review.apartment_fk` foreign key pointing to `apartment.id`.

## JPA orm.xml variant

If instead of pure Hibernate XML you used JPA with XML mappings, the idea was similar: define the entities in Java as plain <mark>POJOs</mark>, then describe table/relationship mapping in `META-INF/orm.xml`. 

There you would configure:

- An `<entity class="...Apartment">`:
  - with an `<one-to-many>` referencing `Review`.
- An `<entity class="...Review">`:
  - with a `<many-to-one>` and `<join-column name="apartment_fk"/>`.

Again, same relational model, just expressed in JPA XML instead of annotations.

## How Spring saw it

Spring before `Boot`:

- Defined `DataSource`, `LocalSessionFactoryBean` (for Hibernate) or `LocalContainerEntityManagerFactoryBean` (for JPA) in Spring XML, pointing to `Apartment.hbm.xml` / `Review.hbm.xml` or `persistence.xml`/`orm.xml`.
- Defined a **transaction manager bean and DAOs/services** that injected `SessionFactory` or `EntityManager`.

So to “create the relationship without annotations” for `Apartment`–`Review`:

- <mark>Entities</mark>: plain Java classes with fields and getters/setters, no `@Entity`, `@OneToMany`, etc.
- <mark>Relationship</mark>: defined in Hibernate `*.hbm.xml` or JPA `orm.xml`.
- Spring: only responsible for wiring the ORM configuration and transactions, not for modeling the association itself.

## References:

1. [13 & Object Relational Mapping (ORM) Data Access](https://docs.spring.io/spring-framework/docs/3.0.1.RELEASE/reference/html/orm.html)
2. [40 & XML Schema-based configuration](https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/xsd-configuration.html)
3. [A Beginner&#039;s Guide to JPA&#039;s persistence.xml](https://thorben-janssen.com/jpa-persistence-xml/)
4. [Mapping JPA to XML](https://docs.oracle.com/middleware/1213/toplink/solutions/jpatoxml.htm)
5. [Using Hibernate ORM and Jakarta Persistence - Quarkus](https://quarkus.io/guides/hibernate-orm)
6. [Working with Relationships in Spring Data REST | Baeldung](https://www.baeldung.com/spring-data-rest-relationships)
7. [Hibernate ORM 5.4.33.Final User Guide](https://docs.hibernate.org/orm/5.4/userguide/html_single)
8. [Define @Type in orm.xml (Spring Data / JPA 2.1 /Hibernate 5.3.7/Postgresql)? - Stack Overflow](https://stackoverflow.com/questions/55046340/define-type-in-orm-xml-spring-data-jpa-2-1-hibernate-5-3-7-postgresql)
9. [The Grails Framework 2.1.1](https://grails.apache.org/docs/2.1.1/guide/single.html)
10. https://docs.spring.io/spring-integration/docs/2.1.0.RELEASE/reference/pdf/spring-integration-reference.pdf
