package com.iaroslaveremeev.service;

import com.iaroslaveremeev.exceptions.ConstraintViolationException;
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
        try {
            this.studentRepository.save(student);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("Student is already added!");
        }
    }

    @Override
    public List<Student> get() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student get(long id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student with this id does not exist"));
    }

    @Override
    public List<Student> getByName(String name) {
        return this.studentRepository.findByName(name);
    }

    @Override
    public List<Student> getByAge(int age) {
        return this.studentRepository.findByAge(age);
    }

    @Override
    public List<Student> getByNum(int num) {
        return this.studentRepository.findByNum(num);
    }

    @Override
    public List<Student> getBySalary(double salary) {
        return this.studentRepository.findBySalary(salary);
    }

    @Override
    public Student delete(long id) {
        Student student = this.get(id);
        this.studentRepository.deleteById(id);
        return student;
    }

    @Override
    public void update(Student student) {
        try {
            this.get(student.getId());
            this.studentRepository.save(student);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("Student with such parameters already exists!");
        }
    }
}