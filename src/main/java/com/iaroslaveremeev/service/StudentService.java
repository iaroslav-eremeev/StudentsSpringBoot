package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Student;

import java.util.List;

public interface StudentService {
    void add(Student student);

    List<Student> get();

    Student get(long id);

    List<Student> getByName(String name);

    List<Student> getByAge(int age);

    List<Student> getByNum(int num);

    List<Student> getBySalary(double salary);

    Student delete(long id);

    void update(Student student);
}
