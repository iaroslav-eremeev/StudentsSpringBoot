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
    /**
     * If a method of this Controller class is successful,
     * the result is included in the response body.
     * If an exception occurs, the error message is included in the response body.
     * In each case a ResponseEntity containing the response status and result is returned.
     */
    private CarService carService;

    /**
     * Setter method called by the Spring framework
     * to inject the CarService instance.
     *
     * @param carService The CarService instance.
     */
    @Autowired
    public void setCarService(CarService carService){
        this.carService = carService;
    }

    /**
     * Adds a new car.
     *
     * @param car The car to be added.
     */
    @PostMapping
    public ResponseEntity<ResponseResult<Car>> add (@RequestBody Car car){
        try {
            this.carService.addCar(car);
            return new ResponseEntity<>(new ResponseResult<>(null, car), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves all cars.
     */
    @GetMapping
    public ResponseEntity<ResponseResult<List<Car>>> get() {
        List<Car> cars = this.carService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, cars), HttpStatus.OK);
    }

    /**
     * Retrieves a car by its ID.
     *
     * @param id The car ID.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Car>> get(@PathVariable long id) {
        try {
            Car car = this.carService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, car), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retrieves a list of cars by brand.
     *
     * @param brand The car brand name.
     */
    @GetMapping("/search/brand")
    public ResponseEntity<ResponseResult<List<Car>>> getCarsByBrand(@RequestParam String brand) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.carService.getCarsByBrand(brand)), HttpStatus.OK);
    }

    /**
     * Retrieves a list of cars by power.
     *
     * @param power The power in horse powers.
     */
    @GetMapping("/search/power")
    public ResponseEntity<ResponseResult<List<Car>>> getCarsByPower(@RequestParam Integer power) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.carService.getCarsByPower(power)), HttpStatus.OK);
    }

    /**
     * Retrieves a list of cars by year of production.
     *
     * @param year The year of car production.
     */
    @GetMapping("/search/year")
    public ResponseEntity<ResponseResult<List<Car>>> getCarsByYear(@RequestParam Integer year) {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.carService.getCarsByYear(year)), HttpStatus.OK);
    }

    /**
     * Retrieves a list of cars by student ID.
     *
     * @param studentId The student ID.
     */
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

    /**
     * Deletes a car by its ID.
     *
     * @param id The car ID.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Car>> delete (@PathVariable long id){
        try {
            Car car = this.carService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, car), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates a car.
     *
     * @param car The car to be updated.
     */
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
