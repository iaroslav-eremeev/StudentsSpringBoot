package com.iaroslaveremeev.service;

import com.iaroslaveremeev.model.Car;
import com.iaroslaveremeev.model.Student;
import com.iaroslaveremeev.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void addCar(Car car) {
        try {
            this.carRepository.save(car);
        } catch (Exception e) {
            throw new IllegalArgumentException("Car is already added!");
        }
    }

    @Override
    public List<Car> get() {
        return this.carRepository.findAll();
    }

    @Override
    public Car get(long id) {
        return this.carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car with this id does not exist"));
    }

    @Override
    public List<Car> getCarsByBrand(String brand) {
        return this.carRepository.getCarsByBrand(brand);
    }

    @Override
    public List<Car> getCarsByPower(Integer power) {
        return this.carRepository.getCarsByPower(power);
    }

    @Override
    public List<Car> getCarsByYear(Integer year) {
        return this.carRepository.getCarsByYear(year);
    }

    @Override
    public List<Car> getCarsByStudent(Student student) {
        return this.carRepository.getCarsByStudent(student);
    }

    @Override
    public Car delete(long id) {
        Car car = this.get(id);
        this.carRepository.deleteById(id);
        return car;
    }

    @Override
    public Car update(Car car) {
        if (car.getBrand().length() == 0 || car.getPower() <= 0 ||
                car.getYear() <= 0 || car.getStudent() == null) {
            throw new IllegalArgumentException("One or more parameters are invalid");
        }
        Car baseCar = this.get(car.getId());
        baseCar.setBrand(car.getBrand());
        baseCar.setPower(car.getPower());
        baseCar.setYear(car.getYear());
        baseCar.setStudent(car.getStudent());
        try {
            this.carRepository.save(baseCar);
            return baseCar;
        } catch (Exception e) {
            throw new IllegalArgumentException("Car with such parameters already exists!");
        }
    }
}

