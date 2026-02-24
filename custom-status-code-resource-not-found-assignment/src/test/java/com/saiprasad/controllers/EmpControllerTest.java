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

import com.saiprasad.repos.EmpRepo;
import com.saiprasad.entities.Employee;

class EmpControllerTest {

    @Mock
    private EmpRepo repo;

    @InjectMocks
    private EmpController controller;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Test when employee exists → 200 OK
    @Test
    void testGetEmployee_Found() {

        Employee emp = new Employee();
        emp.setEid(11);
        emp.setName("Sarthak");
        emp.setAge(25);
        emp.setSalary(25000);
        emp.setDesignation("Tester");

        when(repo.findById(11)).thenReturn(Optional.of(emp));

        ResponseEntity<Employee> response = controller.getEmployee(11);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Sarthak", response.getBody().getName());
    }

    // ✅ Test when employee not found → 204 No Content
    @Test
    void testGetEmployee_NotFound() {

        when(repo.findById(99)).thenReturn(Optional.empty());

        ResponseEntity<Employee> response = controller.getEmployee(99);

        assertEquals(204, response.getStatusCode().value());
    }
}