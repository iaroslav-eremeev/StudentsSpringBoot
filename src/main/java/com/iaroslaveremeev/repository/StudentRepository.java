package com.iaroslaveremeev.repository;

import com.iaroslaveremeev.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> getStudentsByNameAndAgeAndNumAndSalary(String name, Integer age, Integer num, Double salary);

}
