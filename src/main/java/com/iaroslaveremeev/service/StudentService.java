package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Student;

import java.util.List;

public interface StudentService {
    void add(Student student);

    List<Student> get();

    Student get(long id);

    List<Student> getByCriteria(String name, Integer age, Integer num, Double salary);

    Student delete(long id);

    void update(Student student);
}
