package com.iaroslaveremeev.controllers;

import com.iaroslaveremeev.dto.ResponseResult;
import com.iaroslaveremeev.model.Car;
import com.iaroslaveremeev.service.CarService;
import com.iaroslaveremeev.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private CarService carService;
    private StudentService studentService;

    @Autowired
    public void setCarService(CarService carService){
        this.carService = carService;
    }

    @Autowired
    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Car>> add (@RequestBody Car car){
        try {
            this.carService.addCar(car);
            return new ResponseEntity<>(new ResponseResult<>(null, car), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Car>>> get() {
        List<Car> cars = this.carService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, cars), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Car>> get(@PathVariable long id) {
        try {
            Car car = this.carService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, car), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/brand")
    public ResponseEntity<ResponseResult<List<Car>>> getCarsByBrand(@RequestParam String brand) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.carService.getCarsByBrand(brand)), HttpStatus.OK);
    }

    @GetMapping("/search/power")
    public ResponseEntity<ResponseResult<List<Car>>> getCarsByPower(@RequestParam Integer power) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.carService.getCarsByPower(power)), HttpStatus.OK);
    }

    @GetMapping("/search/year")
    public ResponseEntity<ResponseResult<List<Car>>> getCarsByYear(@RequestParam Integer year) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.carService.getCarsByYear(year)), HttpStatus.OK);
    }

    @GetMapping("/search/studentId")
    public ResponseEntity<ResponseResult<List<Car>>> getCarsByStudentId(@RequestParam Long studentId) {
        try {
            return new ResponseEntity<>(new ResponseResult<>(null,
                    this.carService.getCarsByStudentId(studentId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseResult<>("No student with such ID exists",
                    null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Car>> delete (@PathVariable long id){
        try {
            Car car = this.carService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, car), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Car>> update (@RequestBody Car car){
        try {
            Car baseCar = this.carService.update(car);
            return new ResponseEntity<>(new ResponseResult<>(null, baseCar), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

}
