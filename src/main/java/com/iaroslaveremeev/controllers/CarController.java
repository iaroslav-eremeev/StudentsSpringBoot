package com.iaroslaveremeev.controllers;

import com.iaroslaveremeev.dto.ResponseResult;
import com.iaroslaveremeev.model.Car;
import com.iaroslaveremeev.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService){
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Car>> add (@RequestBody Car car){
        try {
            this.carService.add(car);
            return new ResponseEntity<>(new ResponseResult<>(null, car), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
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
            if (car.getId() <= 0){
                return new ResponseEntity<>(new ResponseResult<>("Incorrect id", null),
                        HttpStatus.BAD_REQUEST);
            }
            this.carService.update(car);
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

    @GetMapping("/search")
    public ResponseEntity<ResponseResult<List<Car>>> getByCriteria(@RequestParam(required = false) String brand,
                                                                   @RequestParam(required = false) Integer power,
                                                                   @RequestParam(required = false) Integer year,
                                                                   @RequestParam(required = false) Long idStudent) {
        try {
            List<Car> cars = this.carService.getByCriteria(brand, power, year, idStudent);
            return new ResponseEntity<>(new ResponseResult<>(null, cars), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
