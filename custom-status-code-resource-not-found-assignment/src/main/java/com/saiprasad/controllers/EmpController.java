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
    public Iterable<Employee> getAll() {
        return repo.findAll();
    }
}