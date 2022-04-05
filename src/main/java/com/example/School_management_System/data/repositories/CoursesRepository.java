package com.example.School_management_System.data.repositories;

import com.example.School_management_System.data.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Course, Long> {


}
