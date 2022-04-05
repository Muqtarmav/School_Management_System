package com.example.School_management_System.data.repositories;

import com.example.School_management_System.data.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findById(Long id);

    Optional<Student> findByFirstName(String firstName);

}
