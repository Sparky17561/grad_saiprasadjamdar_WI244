package com.saiprasad.repos;



import org.springframework.data.jpa.repository.JpaRepository;

import com.saiprasad.entities.Employee;


public interface EmpRepo extends JpaRepository<Employee, Integer> {

}