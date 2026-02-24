package com.saiprasad.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import com.saiprasad.entities.Student;
import com.saiprasad.repos.StudentRepo;

@RestController
public class StudentController {

    @Autowired
    StudentRepo repo;
    
    
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    @GetMapping("/")
    public String home() {
        return "index.jsp";   // refers to index.jsp
    }
    // ---------------------------------------------------------
    // GET /students
    // Get all students
    // ---------------------------------------------------------
    @GetMapping("/students")
    public List<Student> getStudents() {
        return repo.findAll();
    }


    // ---------------------------------------------------------
    // GET /students/{regNo}
    // Get specific student
    // ---------------------------------------------------------
    @GetMapping("/students/{regNo}")
    public Optional<Student> getStudent(@PathVariable int regNo) {
        return repo.findById(regNo);
    }


    // ---------------------------------------------------------
    // POST /students
    // Insert student
    // ---------------------------------------------------------
    @PostMapping("/students")
    public String addStudent(@RequestBody Student s) {

        if (repo.existsById(s.getRegNo())) {
            return "Student already exists!";
        }

        repo.save(s);
        return "Student added successfully";
    }


    // ---------------------------------------------------------
    // PUT /students/{regNo}
    // Full update
    // ---------------------------------------------------------
    @PutMapping("/students/{regNo}")
    public String updateStudent(@PathVariable int regNo,
                                @RequestBody Student s) {

        if (s.getRegNo() != regNo) {
            return "Registration numbers don't match!";
        }

        if (!repo.existsById(regNo)) {
            return "Student not found!";
        }

        repo.save(s);
        return "Student updated successfully";
    }


    // ---------------------------------------------------------
    // PATCH /students/{regNo}
    // Partial update
    // ---------------------------------------------------------
    @PatchMapping("/students/{regNo}")
    public String updatePartial(@PathVariable int regNo,
                                @RequestBody Student s) {

        Optional<Student> optional = repo.findById(regNo);

        if (optional.isEmpty()) {
            return "Student not found!";
        }

        Student existing = optional.get();

        if (s.getRollNo() != 0)
            existing.setRollNo(s.getRollNo());

        if (s.getName() != null)
            existing.setName(s.getName());

        if (s.getStandard() != 0)
            existing.setStandard(s.getStandard());

        if (s.getSchool() != null)
            existing.setSchool(s.getSchool());

        if (s.getGender() != null)
            existing.setGender(s.getGender());

        if (s.getPercentage() != 0)
            existing.setPercentage(s.getPercentage());


        repo.save(existing);

        return "Student partially updated successfully";
    }


    // ---------------------------------------------------------
    // DELETE /students/{regNo}
    // ---------------------------------------------------------
    @DeleteMapping("/students/{regNo}")
    public String deleteStudent(@PathVariable int regNo) {

        if (!repo.existsById(regNo)) {
            return "No student found with given regNo";
        }

        repo.deleteById(regNo);
        return "Student deleted successfully";
    }


    // ---------------------------------------------------------
    // GET /students/school?name=KV
    // ---------------------------------------------------------
    @GetMapping("/students/school")
    public List<Student> studentsBySchool(@RequestParam String name) {
        return repo.findStudentsBySchool(name);
    }


    // ---------------------------------------------------------
    // GET /students/school/count?name=DPS
    // ---------------------------------------------------------
    @GetMapping("/students/school/count")
    public long countBySchool(@RequestParam String name) {
        return repo.countStudentsBySchool(name);
    }


    // ---------------------------------------------------------
    // GET /students/school/standard/count?class=5
    // ---------------------------------------------------------
    @GetMapping("/students/school/standard/count")
    public long countByStandard(@RequestParam("class") int standard) {
        return repo.countStudentsByStandard(standard);
    }


    // ---------------------------------------------------------
    // GET /students/result?pass=true/false
    // ---------------------------------------------------------
    @GetMapping("/students/result")
    public List<Student> result(@RequestParam boolean pass) {

        if (pass) {
            return repo.findPassedStudents();
        } else {
            return repo.findFailedStudents();
        }
    }


    // ---------------------------------------------------------
    // GET /students/strength?gender=MALE&standard=5
    // ---------------------------------------------------------
    @GetMapping("/students/strength")
    public long strength(@RequestParam String gender,
                         @RequestParam int standard) {

        return repo.countByGenderAndStandard(gender, standard);
    }

}
