package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    List<Student> get();
    Student get(long id);
    Student delete(long id);
    Student update(Student student);
    List<Student> getStudentsByName(String name);
    List<Student> getStudentsByAge(Integer age);
    List<Student> getStudentsByNum(Integer num);
    List<Student> getStudentsBySalary(Double salary);


}
