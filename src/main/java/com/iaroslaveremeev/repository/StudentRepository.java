package com.iaroslaveremeev.repository;

import com.iaroslaveremeev.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> { }
