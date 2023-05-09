package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Car;
import com.iaroslaveremeev.model.Student;

import java.util.List;

public interface CarService {
    void add(Car car);

    List<Car> get();

    Car get(long id);

    List<Car> getByCriteria(String brand, Integer power, Integer year, Long idStudent);

    Car delete(long id);

    void update(Car car);
}
