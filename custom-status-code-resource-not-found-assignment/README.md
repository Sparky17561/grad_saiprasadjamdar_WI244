
# Custom Status Code - Resource Not Found Assignment (Spring Boot + H2)

## 📌 Project Overview

This is a Spring Boot REST API project demonstrating:

- Spring Boot
- Spring Data JPA
- H2 In-Memory Database
- data.sql initialization
- Custom HTTP status code handling (204 No Content)
- Unit testing using JUnit 5 & Mockito

The application returns:

- **200 OK** → When employee exists  
- **204 No Content** → When employee does not exist  

---

## 🛠 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5
- Mockito

---

## 🗂 Project Structure

```

src/main/java
├── controllers
│    └── EmpController.java
├── entities
│    └── Employee.java
├── repos
│    └── EmpRepo.java

src/main/resources
├── application.properties
└── data.sql

src/test/java
└── EmpControllerTest.java

````

---

## ⚙️ Configuration

### application.properties

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create

spring.h2.console.enabled=true
spring.jpa.defer-datasource-initialization=true
````

---

## 🗄 Database Initialization

The database is populated using `data.sql`.

```sql
insert into EMP (eid, age, salary, name, ROLE) values (11,25,25000,'Sarthak','Tester');
insert into EMP (eid, age, salary, name, ROLE) values (12,45,25300,'Sai','Programmer');
insert into EMP (eid, age, salary, name, ROLE) values (13,15,65000,'Aryaan','Programmer');
insert into EMP (eid, age, salary, name, ROLE) values (14,35,26000,'Sufiyan','Manager');
insert into EMP (eid, age, salary, name, ROLE) values (15,40,265000,'Deep','Manager');
insert into EMP (eid, age, salary, name, ROLE) values (16,50,253000,'Aditya','CEO');
```

The property:

```
spring.jpa.defer-datasource-initialization=true
```

ensures that:

1. Hibernate creates tables first
2. Then `data.sql` runs

---

## 🚀 Running the Application

1. Run the main class
2. Access:

### Get all employees

```
http://localhost:8080/employees
```

### Get employee by ID (Exists)

```
http://localhost:8080/employees/11
```

Response:

```
200 OK
```

### Get employee by ID (Not Exists)

```
http://localhost:8080/employees/99
```

Response:

```
204 No Content
```

---

## 🧪 Testing

Unit tests are written using:

* JUnit 5
* Mockito

Test cases verify:

* Employee found → 200 OK
* Employee not found → 204 No Content

Run tests:

* Right click test file → Run As → JUnit Test

---

## 💡 Key Learning

* Difference between 200, 204, and 404
* How to use ResponseEntity
* How to initialize H2 with data.sql
* How to mock repository layer
* How to write unit tests for controller

---

## 📚 Conclusion

This project demonstrates proper REST API handling using Spring Boot with custom status code handling and in-memory database support.

