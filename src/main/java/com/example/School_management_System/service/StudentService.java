package com.example.School_management_System.service;

import com.example.School_management_System.data.models.Student;
import com.example.School_management_System.dtos.StudentDto;
import com.example.School_management_System.exceptions.StudentDoesNotExistException;
import com.example.School_management_System.exceptions.StudentLogicException;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.web.JsonPath;

import java.util.List;

public interface StudentService {

    List<Student> findAllStudents();
    Student addStudent(StudentDto studentDto) throws StudentLogicException;
    Student findStudentById(Long id) throws StudentDoesNotExistException;
    Student updateStudentById(Long id, JsonPatch student) throws StudentLogicException;
    void delete(Student student);

}
