package com.iaroslaveremeev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "num"})})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name; // Student's first name

    @NonNull
    private int age; // Student's age

    @NonNull
    private int num; // Student personal number

    @NonNull
    private double salary; // Student's salary

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    // If a Student is deleted, associated Cars are also deleted
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    // Preventing circular references
    @JsonIgnore
    private List<Car> cars; // List of cars owned by the student
}
