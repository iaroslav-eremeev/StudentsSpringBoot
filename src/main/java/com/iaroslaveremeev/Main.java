package com.iaroslaveremeev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
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
