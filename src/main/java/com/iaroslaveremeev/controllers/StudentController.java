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
    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Student>> add (@RequestBody Student student){
        try {
            this.studentService.addStudent(student);
            return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Student>>> get() {
        List<Student> students = this.studentService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, students), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Student>> get(@PathVariable long id) {
        try {
            Student student = this.studentService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/name")
    public ResponseEntity<ResponseResult<List<Student>>> getStudentsByName(@RequestParam String name) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.studentService.getStudentsByName(name)), HttpStatus.OK);
    }

    @GetMapping("/search/age")
    public ResponseEntity<ResponseResult<List<Student>>> getStudentsByAge(@RequestParam Integer age) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.studentService.getStudentsByAge(age)), HttpStatus.OK);
    }

    @GetMapping("/search/num")
    public ResponseEntity<ResponseResult<List<Student>>> getStudentsByNum(@RequestParam Integer num) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.studentService.getStudentsByNum(num)), HttpStatus.OK);
    }

    @GetMapping("/search/salary")
    public ResponseEntity<ResponseResult<List<Student>>> getStudentsBySalary(@RequestParam Double salary) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.studentService.getStudentsBySalary(salary)), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Student>> delete (@PathVariable long id){
        try {
            Student student = this.studentService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

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
