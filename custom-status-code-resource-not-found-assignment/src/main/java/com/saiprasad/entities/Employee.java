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

    // Getters & Setters

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