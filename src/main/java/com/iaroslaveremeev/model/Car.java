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
    private String brand; // Car's brand

    @NonNull
    private int power; // Car's power in horse powers (hp)

    @NonNull
    private int year; // Year of car's production

    @ManyToOne // Many cars can be owned by one student
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; // Student who owns the car
}
