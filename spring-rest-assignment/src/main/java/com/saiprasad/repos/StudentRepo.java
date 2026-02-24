package com.saiprasad.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saiprasad.entities.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    // ---------------------------------------------------------
    // GET /students/school?name=KV
    // List all students belonging to that school
    // ---------------------------------------------------------
    @Query("from Student s where s.school = ?1")
    public List<Student> findStudentsBySchool(String school);


    // ---------------------------------------------------------
    // GET /students/school/count?name=DPS
    // Total strength in that school
    // ---------------------------------------------------------
    @Query("select count(s) from Student s where s.school = ?1")
    public long countStudentsBySchool(String school);


    // ---------------------------------------------------------
    // GET /students/school/standard/count?class=5
    // Total number of students in 5th standard
    // ---------------------------------------------------------
    @Query("select count(s) from Student s where s.standard = ?1")
    public long countStudentsByStandard(int standard);


    // ---------------------------------------------------------
    // GET /students/result?pass=true
    // List students who passed (>= 40%) in descending order
    // ---------------------------------------------------------
    @Query("from Student s where s.percentage >= 40 order by s.percentage desc")
    public List<Student> findPassedStudents();


    // ---------------------------------------------------------
    // GET /students/result?pass=false
    // List students who failed (< 40%) in descending order
    // ---------------------------------------------------------
    @Query("from Student s where s.percentage < 40 order by s.percentage desc")
    public List<Student> findFailedStudents();


    // ---------------------------------------------------------
    // GET /students/strength?gender=MALE&standard=5
    // How many Male students in standard 5
    // ---------------------------------------------------------
    @Query("select count(s) from Student s where s.gender = ?1 and s.standard = ?2")
    public long countByGenderAndStandard(String gender, int standard);

}
