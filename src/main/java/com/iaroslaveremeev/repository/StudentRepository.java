package com.iaroslaveremeev.repository;

import com.iaroslaveremeev.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> getStudentsByName(String name);
    List<Student> getStudentsByAge(Integer age);
    List<Student> getStudentsByNum(Integer num);
    List<Student> getStudentsBySalary(Double salary);

}
