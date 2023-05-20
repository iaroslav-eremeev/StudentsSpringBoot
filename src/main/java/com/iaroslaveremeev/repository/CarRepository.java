package com.iaroslaveremeev.repository;

import com.iaroslaveremeev.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> getCarsByBrand(String brand);
    List<Car> getCarsByPower(Integer power);
    List<Car> getCarsByYear(Integer year);
}
