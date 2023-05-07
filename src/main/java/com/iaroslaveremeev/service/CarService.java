package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Car;
import com.iaroslaveremeev.model.Student;

import java.util.List;

public interface CarService {
    void add(Car car);

    List<Car> get();

    Car get(long id);

    List<Car> getByBrand(String brand);

    List<Car> getByPower(int power);

    List<Car> getByYear(int year);

    List<Car> getByIdStudent(long idStudent);

    Car delete(long id);

    void update(Car car);
}
