package com.iaroslaveremeev.controllers;

import com.iaroslaveremeev.dto.ResponseResult;
import com.iaroslaveremeev.model.Student;
import com.iaroslaveremeev.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    /**
     * If a method of this Controller class is successful,
     * the result is included in the response body.
     * If an exception occurs, the error message is included in the response body.
     * In each case a ResponseEntity containing the response status and result is returned.
     */
    private StudentService studentService;

    /**
     * Setter method called by the Spring framework
     * to inject the StudentService instance.
     *
     * @param studentService The StudentService instance.
     */
    @Autowired
    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }

    /**
     * Adds a new student.
     *
     * @param student The student to be added.
     */
    @PostMapping
    public ResponseEntity<ResponseResult<Student>> add (@RequestBody Student student){
        try {
            this.studentService.addStudent(student);
            return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves all students.
     */
    @GetMapping
    public ResponseEntity<ResponseResult<List<Student>>> get() {
        List<Student> students = this.studentService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, students), HttpStatus.OK);
    }

    /**
     * Retrieves a student by their ID.
     *
     * @param id The student ID.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Student>> get(@PathVariable long id) {
        try {
            Student student = this.studentService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves a list of students by first name.
     *
     * @param name The student's first name.
     */
    @GetMapping("/search/name")
    public ResponseEntity<ResponseResult<List<Student>>> getStudentsByName(@RequestParam String name) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.studentService.getStudentsByName(name)), HttpStatus.OK);
    }

    /**
     * Retrieves a list of students by age.
     *
     * @param age The student age.
     */
    @GetMapping("/search/age")
    public ResponseEntity<ResponseResult<List<Student>>> getStudentsByAge(@RequestParam Integer age) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.studentService.getStudentsByAge(age)), HttpStatus.OK);
    }

    /**
     * Retrieves a list of students by student personal number.
     *
     * @param num The student personal number.
     */
    @GetMapping("/search/num")
    public ResponseEntity<ResponseResult<List<Student>>> getStudentsByNum(@RequestParam Integer num) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.studentService.getStudentsByNum(num)), HttpStatus.OK);
    }

    /**
     * Retrieves a list of students by salary.
     *
     * @param salary The student salary.
     */
    @GetMapping("/search/salary")
    public ResponseEntity<ResponseResult<List<Student>>> getStudentsBySalary(@RequestParam Double salary) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.studentService.getStudentsBySalary(salary)), HttpStatus.OK);
    }

    /**
     * Deletes a student by their ID.
     *
     * @param id The student ID.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Student>> delete (@PathVariable long id){
        try {
            Student student = this.studentService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates a student.
     *
     * @param student The student to be updated.
     */
    @PutMapping
    public ResponseEntity<ResponseResult<Student>> update (@RequestBody Student student){
        try {
            Student baseStudent = this.studentService.update(student);
            return new ResponseEntity<>(new ResponseResult<>(null, baseStudent), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

}
