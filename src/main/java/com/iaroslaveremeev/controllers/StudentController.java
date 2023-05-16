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
    public List<Student> getStudentsByName(@RequestParam String name) {
        return this.studentService.getStudentsByName(name);
    }

    @GetMapping("/search/age")
    public List<Student> getStudentsByAge(@RequestParam Integer age) {
        return this.studentService.getStudentsByAge(age);
    }

    @GetMapping("/search/num")
    public List<Student> getStudentsByNum(@RequestParam Integer num) {
        return this.studentService.getStudentsByNum(num);
    }

    @GetMapping("/search/salary")
    public List<Student> getStudentsBySalary(@RequestParam Double salary) {
        return this.studentService.getStudentsBySalary(salary);
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
