# Import .csv to H2

## Summary

H2's `CSVREAD()` function reads CSV files and treats them as temporary tables. The process involves:

1. **File Access**: Place CSV in accessible location like [/tmp/](cci:7://file:///tmp:0:0-0:0) 
2. **Column Mapping**: CSV headers become column names (case-sensitive)
3. **Table Structure**: Use `SHOW COLUMNS` to see actual database column names
4. **Import Syntax**: `INSERT INTO table SELECT columns FROM CSVREAD('/path/file.csv')`
5. **Column Order**: Match database column order in INSERT statement, not CSV order
6. **Data Types**: H2 auto-converts CSV text to appropriate database types
7. **ID Generation**: Use `RANDOM_UUID()` for primary keys

> Key: 
> 
> - Database column names (uppercase, alphabetical) must match CSV headers exactly, or use positional references (`COL1`, `COL2`).
> 
> - Do not use <mark>key-reserved words</mark> like `year`, `user`, etc ..
>   
>   - In this case, if needed use `@Column`

## H2 Import Commands for Apartments

### Step 1: Copy CSV to accessible location

```bash
cp /home/albert/MyProjects/Sandbox/ApartmentPredictorProject/db/Housing.csv /tmp/
```

### Step 2: Check Apartments table structure (if it exists)

```sql
SHOW COLUMNS FROM APARMENT;
```

### Step 3: Create Review table (if it doesn't exist)

```sql
CREATE TABLE IF NOT EXISTS APARTMENT (
    ID VARCHAR(255) PRIMARY KEY,
    PRICE BIGINT,
    AREA INTEGER,
    BEDROOMS INTEGER,
    BATHROOMS INTEGER,
    STORIES INTEGER,
    MAINROAD VARCHAR(255),
    GUESTROOM VARCHAR(255),
    BASEMENT VARCHAR(255),
    HOTWATERHEATING VARCHAR(255),
    AIRCONDITIONING VARCHAR(255),
    PARKING INTEGER,
    PREFAREA VARCHAR(255),
    FURNISHINGSTATUS VARCHAR(255)
);
```

### Step 4: Import CSV data using column positions

```sql
INSERT INTO APARTMENT (ID, AIRCONDITIONING, AREA, BASEMENT, BATHROOMS, BEDROOMS, FURNISHINGSTATUS, GUESTROOM,  HOTWATERHEATING, MAINROAD, PARKING, PREFAREA, PRICE, STORIES)
SELECT
    RANDOM_UUID(),
    AIRCONDITIONING,
    AREA,
    BASEMENT,
    BATHROOMS,
    BEDROOMS,
    FURNISHINGSTATUS,
    GUESTROOM,
    HOTWATERHEATING,
    MAINROAD,
    PARKING,
    PREFAREA,
    PRICE,
    STORIES
FROM CSVREAD('/tmp/Housing.csv');
```

### Step 5: Verify the import

```sql
SELECT COUNT(*) FROM APARTMENT;
SELECT * FROM APARTMENT;
```

## H2 Import Commands for Reviews

### Step 1: Copy CSV to accessible location

```bash
cp /home/albert/MyProjects/Sandbox/ApartmentPredictorProject/Reviews.csv /tmp/Reviews.csv
```

### Step 2: Check Review table structure (if it exists)

```sql
SHOW COLUMNS FROM REVIEW;
```

### Step 3: Create Review table (if it doesn't exist)

```sql
CREATE TABLE IF NOT EXISTS REVIEW (
    ID VARCHAR(255) PRIMARY KEY,
    TITLE VARCHAR(255),
    CONTENT TEXT,
    RATING INTEGER,
    REVIEWDATE DATE
);
```

### Step 4: Import CSV data using column positions

```sql
INSERT INTO REVIEW (ID, CONTENT, RATING, REVIEW_DATE, TITLE)
SELECT
    RANDOM_UUID(),
    CONTENT,
    RATING,
    REVIEWDATE,
    TITLE
FROM CSVREAD('/tmp/Reviews.csv');
```

### Step 5: Verify the import

```sql
SELECT COUNT(*) FROM REVIEW;
SELECT * FROM REVIEW;
```

## H2 Database Data Types

| **Category**  | **Type**          | **Description**         | **Range/Size**                                          |
| ------------- | ----------------- | ----------------------- | ------------------------------------------------------- |
| **Numeric**   | INTEGER/INT       | 32-bit signed integer   | -2,147,483,648 to 2,147,483,647                         |
|               | BIGINT            | 64-bit signed integer   | -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807 |
|               | SMALLINT          | 16-bit signed integer   | -32,768 to 32,767                                       |
|               | TINYINT           | 8-bit signed integer    | -128 to 127                                             |
|               | DECIMAL(p,s)      | Fixed-point decimal     | Precision p, Scale s                                    |
|               | DOUBLE/FLOAT      | Double precision float  | 64-bit floating point                                   |
|               | REAL              | Single precision float  | 32-bit floating point                                   |
| **String**    | VARCHAR(n)        | Variable-length string  | Up to n characters                                      |
|               | CHAR(n)           | Fixed-length string     | Exactly n characters                                    |
|               | TEXT/CLOB         | Large text object       | Unlimited length                                        |
|               | NVARCHAR(n)       | Unicode variable string | Up to n Unicode characters                              |
| **Date/Time** | <mark>DATE</mark> | Date only               | <mark>YYYY-MM-DD</mark>                                 |
|               | TIME              | Time only               | HH:MM:SS                                                |
|               | TIMESTAMP         | Date and time           | YYYY-MM-DD HH:MM:SS                                     |
|               | DATETIME          | Alias for TIMESTAMP     | YYYY-MM-DD HH:MM:SS                                     |
| **Binary**    | BINARY(n)         | Fixed-length binary     | Exactly n bytes                                         |
|               | VARBINARY(n)      | Variable-length binary  | Up to n bytes                                           |
|               | BLOB              | Binary large object     | Unlimited size                                          |
| **Other**     | BOOLEAN           | Boolean value           | TRUE/FALSE                                              |
|               | UUID              | Unique identifier       | 128-bit UUID                                            |
|               | ARRAY             | Array of values         | Variable size array                                     |
|               | JSON              | JSON data               | JSON format (H2 2.x+)                                   |
| **Auto**      | IDENTITY          | Auto-increment integer  | Auto-generated primary key                              |
|               | AUTO_INCREMENT    | Alias for IDENTITY      | Auto-generated sequence                                 |
