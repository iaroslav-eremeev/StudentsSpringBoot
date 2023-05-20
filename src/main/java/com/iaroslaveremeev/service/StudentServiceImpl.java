package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Student;
import com.iaroslaveremeev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    /**
     * Setter method called by the Spring framework
     * to inject the StudentRepository instance.
     */
    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Adds a new student to the repository.
     *
     * @param student The student to be added.
     * @throws IllegalArgumentException If the student is already added.
     */
    @Override
    public void addStudent(Student student) {
        try {
            this.studentRepository.save(student);
        } catch (Exception e) {
            throw new IllegalArgumentException("Student is already added!");
        }
    }

    /**
     * Retrieves all students from the repository.
     *
     * @return A list of all students.
     */
    @Override
    public List<Student> get() {
        return this.studentRepository.findAll();
    }

    /**
     * Retrieves a student by its ID from the repository.
     *
     * @param id The student ID.
     * @return The student with the specified ID.
     * @throws IllegalArgumentException
     * If the student with the specified ID does not exist.
     */
    @Override
    public Student get(long id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student with this id does not exist"));
    }

    /**
     * Retrieves a list of students by first name from the repository.
     *
     * @param name The first name of the student.
     * @return A list of students with the specified first name.
     */
    @Override
    public List<Student> getStudentsByName(String name) {
        return this.studentRepository.getStudentsByName(name);
    }

    /**
     * Retrieves a list of students by age from the repository.
     *
     * @param age The age of the student.
     * @return A list of students with the specified age.
     */
    @Override
    public List<Student> getStudentsByAge(Integer age) {
        return this.studentRepository.getStudentsByAge(age);
    }

    /**
     * Retrieves a list of students by personal number from the repository.
     *
     * @param num The personal number of the student.
     * @return A list of students with the specified personal number.
     */
    @Override
    public List<Student> getStudentsByNum(Integer num) {
        return this.studentRepository.getStudentsByNum(num);
    }

    /**
     * Retrieves a list of students by salary from the repository.
     *
     * @param salary The salary of the student.
     * @return A list of students with the specified salary.
     */
    @Override
    public List<Student> getStudentsBySalary(Double salary) {
        return this.studentRepository.getStudentsBySalary(salary);
    }

    /**
     * Deletes a student by its ID from the repository.
     *
     * @param id The ID of the student to be deleted.
     * @return The deleted student.
     * @throws IllegalArgumentException
     * If the student with the specified ID does not exist.
     */
    @Override
    public Student delete(long id) {
        Student student = this.get(id);
        this.studentRepository.deleteById(id);
        return student;
    }

    /**
     * Updates the details of a student in the repository.
     *
     * @param student The updated student.
     * @return The updated student.
     * @throws IllegalArgumentException
     * If one or more parameters are invalid or
     * if a student with the same parameters already exists.
     */
    @Override
    public Student update(Student student) {
        if (student.getName().length() == 0 || student.getAge() <= 0 ||
                student.getNum() <= 0 || student.getSalary() <= 0) {
            throw new IllegalArgumentException("One or more parameters are invalid");
        }
        Student baseStudent = this.get(student.getId());
        baseStudent.setName(student.getName());
        baseStudent.setAge(student.getAge());
        baseStudent.setNum(student.getNum());
        baseStudent.setSalary(student.getSalary());
        try {
            this.studentRepository.save(baseStudent);
            return baseStudent;
        } catch (Exception e) {
            throw new IllegalArgumentException("Student with such parameters already exists!");
        }
    }
}
