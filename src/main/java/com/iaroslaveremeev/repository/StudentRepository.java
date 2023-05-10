package com.iaroslaveremeev.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iaroslaveremeev.exceptions.ConstraintViolationException;
import com.iaroslaveremeev.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    @Value("${datasource.studentsFilename}")
    private String filename;
    private Map<Long, Student> studentHashMap = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        try {
            ArrayList<Student> list = this.objectMapper.readValue(new File(filename), new TypeReference<>() {
            });
            this.studentHashMap = list.stream().collect(Collectors.toMap(Student::getId, x -> x));
        } catch (IOException ignored) {
        }
    }

    public void save(Student student) throws ConstraintViolationException {
        // Перенесено в сервис
        /*if (student.getName() == null || student.getAge() <= 0 ||
                student.getNum() <= 0 || student.getSalary() <= 0) {
            throw new ConstraintViolationException("One or more parameters are invalid");
        }*/
        if (this.studentHashMap.values().stream().anyMatch(x -> x.getName()
                .equals(student.getName()) && x.getAge() == student.getAge()
                && x.getNum() == student.getNum()
                && x.getSalary() == student.getSalary()
                && x.getId() != student.getId())) {
            throw new ConstraintViolationException("Duplicate entry");
        }
        if(student.getId() == 0) {
            long id = studentHashMap.keySet().stream().mapToLong(x -> x).max().orElse(0L) + 1;
            student.setId(id);
        }
        this.studentHashMap.put(student.getId(), student);
        this.save();
    }

    private void save() {
        try {
            this.objectMapper.writeValue(new File(this.filename), this.studentHashMap.values());
        } catch (IOException ignored) {
        }
    }

    public List<Student> findAll() {
        return new ArrayList<>(this.studentHashMap.values());
    }

    public Optional<Student> findById(long id) {
        return Optional.ofNullable(this.studentHashMap.get(id));
    }

    public List<Student> findStudents(String name, Integer age, Integer num, Double salary) {
        return this.studentHashMap.values().stream()
                .filter(student ->
                        (name == null || student.getName().equals(name)) &&
                                (age == null || student.getAge() == age) &&
                                (num == null || student.getNum() == num) &&
                                (salary == null || student.getSalary() == salary)
                )
                .collect(Collectors.toList());
    }

    public void deleteById(long id) {
        this.studentHashMap.remove(id);
        this.save();
    }
}
