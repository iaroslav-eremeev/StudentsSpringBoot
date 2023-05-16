package com.iaroslaveremeev.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iaroslaveremeev.model.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/*@Repository
public class CarRepository {
    @Value("${datasource.carsFilename}")
    private String filename;
    private Map<Long, Car> carHashMap = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        try {
            ArrayList<Car> list = this.objectMapper.readValue(new File(filename), new TypeReference<>() {
            });
            this.carHashMap = list.stream().collect(Collectors.toMap(Car::getId, x -> x));
        } catch (IOException ignored) {
        }
    }

    public void save(Car car) throws ConstraintViolationException {
        if (car.getBrand() == null || car.getPower() <= 0 ||
                car.getYear() <= 0 || car.getIdStudent() <= 0) {
            throw new ConstraintViolationException("One or more parameters are invalid");
        }
        if (this.carHashMap.values().stream().anyMatch(x -> x.getBrand()
                .equals(car.getBrand()) && x.getPower() == car.getPower()
                && x.getYear() == car.getYear()
                && x.getIdStudent() == car.getIdStudent()
                && x.getId() != car.getId())) {
            throw new ConstraintViolationException("Duplicate entry");
        }
        if(car.getId() == 0) {
            long id = carHashMap.keySet().stream().mapToLong(x -> x).max().orElse(0L) + 1;
            car.setId(id);
        }
        this.carHashMap.put(car.getId(), car);
        this.save();
    }

    private void save() {
        try {
            this.objectMapper.writeValue(new File(this.filename), this.carHashMap.values());
        } catch (IOException ignored) {
        }
    }

    public List<Car> findAll() {
        return new ArrayList<>(this.carHashMap.values());
    }

    public Optional<Car> findById(long id) {
        return Optional.ofNullable(this.carHashMap.get(id));
    }

    public List<Car> findCars(String brand, Integer power, Integer year, Long idStudent) {
        return this.carHashMap.values().stream()
                .filter(car ->
                        (brand == null || car.getBrand().equals(brand)) &&
                                (power == null || car.getPower() == power) &&
                                (year == null || car.getYear() == year) &&
                                (idStudent == null || car.getIdStudent() == idStudent)
                )
                .collect(Collectors.toList());
    }

    public void deleteById(long id) {
        this.carHashMap.remove(id);
        this.save();
    }

    public void deleteAllByStudentId(long idStudent) {
        this.carHashMap = this.carHashMap.values().stream()
                .filter(car -> car.getIdStudent() != idStudent)
                .collect(Collectors.toMap(Car::getId, car -> car));
    }
}*/
