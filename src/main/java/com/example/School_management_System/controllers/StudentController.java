package com.example.School_management_System.controllers;


import com.example.School_management_System.data.models.Student;
import com.example.School_management_System.data.repositories.StudentRepository;
import com.example.School_management_System.dtos.StudentDto;
import com.example.School_management_System.exceptions.StudentDoesNotExistException;
import com.example.School_management_System.exceptions.StudentLogicException;
import com.example.School_management_System.service.StudentService;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping()
    public ResponseEntity<?> findAllStudent(){
        List<Student> studentList = studentService.findAllStudents();
        return ResponseEntity.ok().body(studentList);
    }

    @GetMapping("get/student/Id")
        public ResponseEntity<?> findStudentById(Long Id){

         try {
            Student savedStudent = studentService.findStudentById(Id);
            return ResponseEntity.ok().body(savedStudent);
        }
         catch(StudentDoesNotExistException message){
             return ResponseEntity.badRequest().body(message);

        }

    }

    @PostMapping("add/")
    public ResponseEntity<?> addStudents(@RequestBody StudentDto studentDto){

        log.info("student added::{}", studentDto);

        try{
            Student student = studentService.addStudent(studentDto);
            return ResponseEntity.ok().body(student);
        }
        catch(StudentLogicException message){
            return ResponseEntity.badRequest().body(message);
        }

    }


    @PatchMapping(path = "{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateStudentById(@PathVariable Long Id, @RequestBody JsonPatch studentPatch){

        try{
            Student updatedStudent = studentService.updateStudentById(Id, studentPatch);
            log.info("updated student {}", updatedStudent);
            return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
        }
        catch (StudentLogicException e ){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return null;
    }

}
