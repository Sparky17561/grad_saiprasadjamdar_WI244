
# ✅ **Jenkins CI/CD Notes**

## 🔹 What is Jenkins?

**Jenkins** is a **CI/CD tool** used for:

* **Continuous Integration (CI)**
* **Continuous Deployment (CD)**

It automates building, testing, and deploying applications.

---

## 🔹 Why Do We Need a CI/CD Pipeline?

We need a CI/CD pipeline **to automate integration and deployment** of code changes.

Without CI/CD:

* Developers manually build code
* Tests run manually
* Deployment is manual and error-prone

With CI/CD:

* Code push → Auto build → Auto test → Auto deploy

This saves:
✔ Time
✔ Human effort
✔ Errors

---

## 🔹 CI/CD Pipeline Concept

When developer pushes code:

1. Code is integrated into repository
2. Jenkins pulls latest code
3. Build runs automatically
4. Tests run automatically
5. Application deploys automatically

This process is called a **CI/CD pipeline**.

---

## 🔹 Jenkins History

* **Sun Microsystems → Hudson**
* Later **Oracle → Jenkins**

Jenkins is basically an improved version of Hudson.

---

## 🔹 Advantages of Jenkins

Compared to other CI/CD tools:

Examples of CI/CD tools:

* Travis CI
* GitLab CI
* CircleCI
* Bamboo
* TeamCity
* Semaphore
* Buddy
* Docker pipelines

### ✔ Why Jenkins is popular:

* Open source & free
* Works on all platforms
* Supports almost every programming language
* Huge plugin ecosystem
* Integrates with any tool (GitHub, Docker, AWS, Maven, etc.)
* Highly customizable

👉 Jenkins is **generic** and works with almost every platform/tool.

---

## 🔹 Running WAR Files

A **WAR file** is a packaged Java web application.

It needs a **server** to run.

Examples:

* Jetty
* Tomcat
* WildFly

Java often runs WAR using embedded server.

### Run WAR file:

```bash
java -jar file_name.war
```

If using newer Java versions:

```bash
java -jar --enable-preview file_name.war
```

(No main class needed because server runs it.)

---

## 🔹 Jetty Server

Jetty is a lightweight Java web server.

We can deploy web apps using Jetty directly.

Example:

```bash
java -jar myapp.war
```

Jetty starts and hosts the application.

---

## 🔹 Configuring Tools in Jenkins

To configure tools like Maven, Git, Java:

Go to:

```
Manage Jenkins → Global Tool Configuration
```

Here you configure:

* JDK
* Maven
* Git
* Node
* etc.

Jenkins then uses these tools during pipeline execution.



# 📘 Custom Status Code – Resource Not Found

(Spring Boot + H2 + JPA + Mockito)

---

# 📌 1. Project Overview

This project demonstrates a REST API built using **Spring Boot** that:

* Uses **Spring Data JPA**
* Uses **H2 In-Memory Database**
* Loads initial data using `data.sql`
* Returns a **custom HTTP status code (204 No Content)** when a resource is not found
* Includes **unit testing** using JUnit 5 and Mockito

---

# 🎯 2. Objective

When fetching an employee by ID:

| Scenario                | HTTP Status      |
| ----------------------- | ---------------- |
| Employee exists         | `200 OK`         |
| Employee does not exist | `204 No Content` |

Instead of returning `404`, we intentionally return `204` to indicate:

> The request was valid, but there is no content to return.

---

# 🛠 3. Technologies Used

* Java 17
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven
* JUnit 5
* Mockito

---

# 📁 4. Project Structure

```
src/main/java
 └── com.saiprasad
      ├── CustomStatusCodeResourceNotFoundAssignmentApplication.java
      ├── controllers
      │     └── EmpController.java
      ├── entities
      │     └── Employee.java
      └── repos
            └── EmpRepo.java

src/main/resources
 ├── application.properties
 └── data.sql

src/test/java
 └── com.saiprasad.controllers
       └── EmpControllerTest.java
```

---

# 🧱 5. Source Code Files

---

## ✅ 5.1 Main Application Class

```java
package com.saiprasad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomStatusCodeResourceNotFoundAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomStatusCodeResourceNotFoundAssignmentApplication.class, args);
    }
}
```

---

## ✅ 5.2 Entity Class – Employee

```java
package com.saiprasad.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "EMP")
public class Employee {

    @Id
    private int eid;

    private int age;
    private int salary;
    private String name;

    @Column(name = "ROLE")
    private String designation;

    public Employee() {}

    public int getEid() { return eid; }
    public void setEid(int eid) { this.eid = eid; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
}
```

---

## ✅ 5.3 Repository Interface

```java
package com.saiprasad.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.saiprasad.entities.Employee;

public interface EmpRepo extends JpaRepository<Employee, Integer> {

}
```

---

## ✅ 5.4 REST Controller

```java
package com.saiprasad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saiprasad.entities.Employee;
import com.saiprasad.repos.EmpRepo;

@RestController
@RequestMapping("/employees")
public class EmpController {

    @Autowired
    private EmpRepo repo;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {

        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        return repo.findAll();
    }
}
```

---

# ⚙ 6. Configuration File

## application.properties

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create

spring.h2.console.enabled=true
spring.jpa.defer-datasource-initialization=true
```

---

# 🗄 7. Database Initialization

## data.sql

```sql
insert into EMP (eid, age, salary, name, ROLE) values (11,25,25000,'Sarthak','Tester');
insert into EMP (eid, age, salary, name, ROLE) values (12,45,25300,'Sai','Programmer');
insert into EMP (eid, age, salary, name, ROLE) values (13,15,65000,'Aryaan','Programmer');
insert into EMP (eid, age, salary, name, ROLE) values (14,35,26000,'Sufiyan','Manager');
insert into EMP (eid, age, salary, name, ROLE) values (15,40,265000,'Deep','Manager');
insert into EMP (eid, age, salary, name, ROLE) values (16,50,253000,'Aditya','CEO');
```

---

# 🔍 8. API Testing

### Get All Employees

```
http://localhost:8080/employees
```

### Get Existing Employee

```
http://localhost:8080/employees/11
```

Response:

```
200 OK
```

---

### Get Non-Existing Employee

```
http://localhost:8080/employees/99
```

Response:

```
204 No Content
```

---

# 🧪 9. Unit Testing

## EmpControllerTest.java

```java
package com.saiprasad.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.saiprasad.entities.Employee;
import com.saiprasad.repos.EmpRepo;

class EmpControllerTest {

    @Mock
    private EmpRepo repo;

    @InjectMocks
    private EmpController controller;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEmployeeFound() {

        Employee emp = new Employee();
        emp.setEid(11);
        emp.setName("Sarthak");

        when(repo.findById(11)).thenReturn(Optional.of(emp));

        ResponseEntity<Employee> response = controller.getEmployee(11);

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void testEmployeeNotFound() {

        when(repo.findById(99)).thenReturn(Optional.empty());

        ResponseEntity<Employee> response = controller.getEmployee(99);

        assertEquals(204, response.getStatusCode().value());
    }
}
```

# ✅ **Calculator CI/CD Assignment – Complete Guide**

This guide shows how to build a **Java Maven project**, push to GitHub, and configure **Jenkins CI with webhook** so every code change builds automatically.

Works with:

• STS (Spring Tool Suite)
• Maven
• GitHub
• Jenkins
• ngrok
• JUnit

---

# ✅ **STEP 0 — Install Requirements**

Install:

• JDK 17
• STS / Eclipse
• Maven
• Git
• Jenkins
• ngrok

Check:

```bash
java -version
mvn -version
git --version
```

Start Jenkins:

```
http://localhost:8080
```

---

# ✅ **STEP 1 — Create Maven Project in STS**

In STS:

```
File → New → Maven Project
✔ Create simple project
```

Fill:

```
GroupId    : com.saiprasad
ArtifactId : calculator-ci
Packaging  : jar
```

Finish.

---

# ✅ **STEP 2 — Create Java Files**

Create package:

```
com.saiprasad.calculator_ci
```

### 📄 Calculator.java

```java
package com.saiprasad.calculator_ci;

public class Calculator {

    public int add(int a, int b){
        return a + b;
    }

    public int sub(int a, int b){
        return a - b;
    }

    public int mul(int a, int b){
        return a * b;
    }

    public int div(int a, int b){
        if(b == 0){
            throw new ArithmeticException("Divide by zero");
        }
        return a / b;
    }
}
```

---

### 📄 CalculatorMain.java

```java
package com.saiprasad.calculator_ci;

public class CalculatorMain {
    public static void main(String[] args) {

        Calculator calc = new Calculator();

        System.out.println("Add = " + calc.add(10,5));
        System.out.println("Sub = " + calc.sub(10,5));
        System.out.println("Mul = " + calc.mul(10,5));
        System.out.println("Div = " + calc.div(10,5));
    }
}
```

---

# ✅ **STEP 3 — Add JUnit Tests**

Create inside `src/test/java/com/saiprasad/calculator_ci`

```java
package com.saiprasad.calculator_ci;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    void testAdd(){
        assertEquals(15, calc.add(10,5));
    }

    @Test
    void testSub(){
        assertEquals(5, calc.sub(10,5));
    }

    @Test
    void testMul(){
        assertEquals(50, calc.mul(10,5));
    }

    @Test
    void testDiv(){
        assertEquals(2, calc.div(10,5));
    }

    @Test
    void testDivideByZero(){
        Exception e = assertThrows(
            ArithmeticException.class,
            () -> calc.div(10,0)
        );
        assertEquals("Divide by zero", e.getMessage());
    }
}
```

---

# ✅ **STEP 4 — Add JUnit to pom.xml**

Inside `<dependencies>`:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
    <scope>test</scope>
</dependency>
```

Add exec plugin:

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>3.1.0</version>
</plugin>
```

Update Maven Project.

---

# ✅ **STEP 5 — Build in STS**

Right click project → Run As → Maven Install

OR

```bash
mvn clean package
```

You should see:

```
BUILD SUCCESS
```

---

# ✅ **STEP 6 — Push to GitHub**

Inside project folder:

```bash
git init
git add .
git commit -m "Initial calculator project"
git remote add origin https://github.com/USERNAME/calculator-ci.git
git push -u origin main
```

---

# ✅ **STEP 7 — Add .gitignore**

Create `.gitignore`:

```
target/
.settings/
.classpath
.project
```

Commit again.

---

# ✅ **STEP 8 — Install Jenkins Plugins**

Manage Jenkins → Plugins

Install:

• Git Plugin
• GitHub Plugin
• Maven Integration Plugin

Restart Jenkins.

---

# ✅ **STEP 9 — Configure Maven in Jenkins**

Manage Jenkins → Global Tool Configuration

Add Maven:

```
Name: Maven-3
✔ Install automatically
```

---

# ✅ **STEP 10 — Create Jenkins Job**

New Item → Freestyle → calculator-ci

### Source Code Management

Git Repo URL:

```
https://github.com/USERNAME/calculator-ci.git
```

Branch:

```
*/main
```

---

### Build Trigger

✔ GitHub hook trigger for GITScm polling

---

### Build Steps

1️⃣ Maven step:

```
clean package
```

2️⃣ Maven step:

```
exec:java -Dexec.mainClass=com.saiprasad.calculator_ci.CalculatorMain
```

Save.

---

# ✅ **STEP 11 — Setup ngrok**

Download ngrok → extract.

Add token:

```bash
ngrok config add-authtoken YOUR_TOKEN
```

Run:

```bash
ngrok http 8080
```

Copy HTTPS URL.

---

# ✅ **STEP 12 — Add GitHub Webhook**

GitHub → Repo → Settings → Webhooks → Add

Payload URL:

```
https://xxxxx.ngrok-free.dev/github-webhook/
```

Content type: application/json
Events: Just push

Save.

---

# ✅ **STEP 13 — Test CI**

Make change → commit → push:

```bash
git add .
git commit -m "test webhook"
git push
```

Jenkins should start automatically.

---

# ✅ **STEP 14 — Expected Output**

Jenkins Console:

```
Tests run: 5, Failures: 0
Add = 15
Sub = 5
Mul = 50
Div = 2
BUILD SUCCESS
```

---

# ✅ **STEP 15 — How CI Works**

1. You push code
2. GitHub webhook → Jenkins
3. Jenkins pulls repo
4. Maven builds
5. JUnit tests run
6. App runs

This is Continuous Integration.
