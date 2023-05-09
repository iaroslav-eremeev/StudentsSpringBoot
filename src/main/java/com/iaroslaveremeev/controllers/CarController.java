package com.iaroslaveremeev.controllers;

import com.iaroslaveremeev.dto.ResponseResult;
import com.iaroslaveremeev.model.Car;
import com.iaroslaveremeev.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



    /**
     * Техническое задание:
     * 1.	Создать модель данных студент(id, fio, age, num, salary),
     * автомобиль(id, brand, power, year, idStudent),
     * репозитории и сервисы к ним для загрузки, сохранения, обновления и удаления данных из json файлов
     * 2.	Для сущностей Student и Auto реализовать контроллеры, которые задают API для:
     * •	Добавления, принять json объект сущности без id
     * •	Удаления. Принимается id сущности в пути запроса
     * •	Обновления, принять json объект сущности с id
     * •	Получения данных по id, всех объектов сущности, всех объектов сущности по какому-то полю
     * 	Все методы API должны возвращать клиенту json объект ResponseResult
     */

}
