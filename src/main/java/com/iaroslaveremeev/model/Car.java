package com.iaroslaveremeev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private long id;
    private String brand;
    private int power;
    private int year;
    private long idStudent;

}
