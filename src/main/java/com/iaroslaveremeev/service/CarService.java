package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Car;
import com.iaroslaveremeev.model.Student;

import java.util.List;

public interface CarService {
    void addCar(Car car);
    List<Car> get();
    Car get(long id);
    Car delete(long id);
    Car update(Car Car);
    List<Car> getCarsByBrand(String brand);
    List<Car> getCarsByPower(Integer power);
    List<Car> getCarsByYear(Integer year);
    List<Car> getCarsByStudentId(Long studentId);
}
