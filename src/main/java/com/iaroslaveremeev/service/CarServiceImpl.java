package com.iaroslaveremeev.service;

import com.iaroslaveremeev.exceptions.ConstraintViolationException;
import com.iaroslaveremeev.model.Car;
import com.iaroslaveremeev.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void add(Car car) {
        try {
            this.carRepository.save(car);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("TV has already added!");
        }
    }

    @Override
    public List<Car> get() {
        return this.carRepository.findAll();
    }

    @Override
    public Car get(long id) {
        return this.carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("This car does not exist!"));
    }

    @Override
    public List<Car> getByCriteria(String brand, Integer power, Integer year, Long idStudent) {
        return this.carRepository.findCars(brand, power, year, idStudent);
    }

    @Override
    public Car delete(long id) {
        Car car = this.get(id);
        this.carRepository.deleteById(id);
        return car;
    }

    @Override
    public void deleteAllByStudentId(long idStudent){
        this.carRepository.deleteAllByStudentId(idStudent);
    }

    @Override
    public void update(Car car) {
        try {
            this.get(car.getId());
            this.carRepository.save(car);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("Some parameters are invalid or car with such parameters already exists!");
        }
    }
}
