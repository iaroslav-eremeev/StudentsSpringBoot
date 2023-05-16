package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    List<Student> get();
    Student get(long id);
    Student delete(long id);
    Student update(Student student);
    List<Student> get(String name, Integer age, Integer num, Double salary);

}
