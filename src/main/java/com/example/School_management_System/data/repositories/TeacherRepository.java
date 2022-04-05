package com.example.School_management_System.data.repositories;

import com.example.School_management_System.data.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findById(Long id);

}
