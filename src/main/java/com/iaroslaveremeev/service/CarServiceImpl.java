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
    private StudentService studentService;

    /**
     * Setter method called by the Spring framework
     * to inject the CarRepository instance.
     */
    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Setter method called by the Spring framework
     * to inject the StudentRepository instance.
     */
    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Adds a new car to the repository.
     *
     * @param car The car to be added.
     * @throws IllegalArgumentException If the car is already added.
     */
    @Override
    public void addCar(Car car) {
        try {
            this.carRepository.save(car);
        } catch (Exception e) {
            throw new IllegalArgumentException("Car is already added!");
        }
    }

    /**
     * Retrieves all cars from the repository.
     *
     * @return A list of all cars.
     */
    @Override
    public List<Car> get() {
        return this.carRepository.findAll();
    }

    /**
     * Retrieves a car by its ID from the repository.
     *
     * @param id The car ID.
     * @return The car with the specified ID.
     * @throws IllegalArgumentException
     * If the car with the specified ID does not exist.
     */
    @Override
    public Car get(long id) {
        return this.carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car with this id does not exist"));
    }

    /**
     * Retrieves a list of cars by brand from the repository.
     *
     * @param brand The brand name.
     * @return A list of cars with the specified brand.
     */
    @Override
    public List<Car> getCarsByBrand(String brand) {
        return this.carRepository.getCarsByBrand(brand);
    }

    /**
     * Retrieves a list of cars by power from the repository.
     *
     * @param power The power in horse powers.
     * @return A list of cars with the specified power.
     */
    @Override
    public List<Car> getCarsByPower(Integer power) {
        return this.carRepository.getCarsByPower(power);
    }

    /**
     * Retrieves a list of cars by year of production from the repository.
     *
     * @param year The year of car production.
     * @return A list of cars with the specified year of production.
     */
    @Override
    public List<Car> getCarsByYear(Integer year) {
        return this.carRepository.getCarsByYear(year);
    }

    /**
     * Retrieves a list of cars by student ID.
     *
     * @param studentId The ID of the student.
     * @return A list of cars associated with the student.
     * @throws IllegalArgumentException
     * If the student with the specified ID does not exist.
     */
    @Override
    public List<Car> getCarsByStudentId(Long studentId) {
        Student student = this.studentService.get(studentId);
        return student.getCars();
    }

    /**
     * Deletes a car by its ID from the repository.
     *
     * @param id The ID of the car to be deleted.
     * @return The deleted car.
     * @throws IllegalArgumentException
     * If the car with the specified ID does not exist.
     */
    @Override
    public Car delete(long id) {
        Car car = this.get(id);
        this.carRepository.deleteById(id);
        return car;
    }

    /**
     * Updates the details of a car in the repository.
     *
     * @param car The updated car.
     * @return The updated car.
     * @throws IllegalArgumentException
     * If one or more parameters are invalid or
     * if a car with the same parameters already exists.
     */
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

