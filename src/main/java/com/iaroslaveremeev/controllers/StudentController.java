package com.iaroslaveremeev.controllers;

import com.iaroslaveremeev.dto.ResponseResult;
import com.iaroslaveremeev.model.Student;
import com.iaroslaveremeev.service.CarService;
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
    private CarService carService;

    @Autowired
    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }

    @Autowired
    public void setCarService(CarService carService) {this.carService = carService; }

    @PostMapping
    public ResponseEntity<ResponseResult<Student>> add (@RequestBody Student student){
        try {
            this.studentService.add(student);
            return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Student>> delete (@PathVariable long id){
        try {
            Student student = this.studentService.delete(id);
            this.carService.deleteAllByStudentId(id);
            return new ResponseEntity<>(new ResponseResult<>(null, student), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Student>> update (@RequestBody Student student){
        try {
            if (student.getId() <= 0){
                return new ResponseEntity<>(new ResponseResult<>("Incorrect id", null),
                        HttpStatus.BAD_REQUEST);
            }
            this.studentService.update(student);
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

    @GetMapping("/search")
    public ResponseEntity<ResponseResult<List<Student>>> getByCriteria(@RequestParam(required = false) String name,
                                                                   @RequestParam(required = false) Integer age,
                                                                   @RequestParam(required = false) Integer num,
                                                                   @RequestParam(required = false) Double salary) {
        try {
            List<Student> students = this.studentService.getByCriteria(name, age, num, salary);
            return new ResponseEntity<>(new ResponseResult<>(null, students), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
