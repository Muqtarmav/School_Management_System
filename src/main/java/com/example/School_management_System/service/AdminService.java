package com.example.School_management_System.service;

import com.example.School_management_System.data.models.Teacher;

import java.util.List;

public interface AdminService {

    List<Teacher> findAll();
    Teacher addTeacher (Teacher teacher);
    Teacher findById(Long id);
    void delete(Teacher teacher);



}
