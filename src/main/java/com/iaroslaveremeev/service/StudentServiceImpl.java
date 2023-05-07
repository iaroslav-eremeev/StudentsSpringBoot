package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Student;
import com.iaroslaveremeev.repository.CarRepository;
import com.iaroslaveremeev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void add(Student student) {
        this.studentRepository.
    }

    @Override
    public List<Student> get() {
        return null;
    }

    @Override
    public Student get(long id) {
        return null;
    }

    @Override
    public List<Student> getByName(String name) {
        return null;
    }

    @Override
    public List<Student> getByAge(int age) {
        return null;
    }

    @Override
    public List<Student> getByNum(int num) {
        return null;
    }

    @Override
    public List<Student> getBySalary(double salary) {
        return null;
    }

    @Override
    public Student delete(long id) {
        return null;
    }

    @Override
    public void update(Student student) {

    }
}
