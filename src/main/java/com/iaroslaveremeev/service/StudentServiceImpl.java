package com.iaroslaveremeev.service;

import com.iaroslaveremeev.exceptions.ConstraintViolationException;
import com.iaroslaveremeev.model.Student;
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
    public List<Student> getByCriteria(String name, Integer age, Integer num, Double salary){
        return this.studentRepository.findStudents(name, age, num, salary);
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
            if (student.getName() == null || student.getAge() <= 0 ||
                    student.getNum() <= 0 || student.getSalary() <= 0) {
                throw new IllegalArgumentException("One or more parameters are invalid");
            }
            this.get(student.getId());
            this.studentRepository.save(student);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("Student with such parameters already exists!");
        }
    }
}
