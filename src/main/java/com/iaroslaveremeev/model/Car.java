package com.iaroslaveremeev.model;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String brand;
    @NonNull
    private int power;
    @NonNull
    private int year;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}
