package com.example.School_management_System.service;

import com.example.School_management_System.data.models.Teacher;
import com.example.School_management_System.dtos.TeacherDto;
import com.example.School_management_System.exceptions.TeacherDoesNotExistException;
import com.example.School_management_System.exceptions.TeacherLogicException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

public interface TeacherService {

    List<Teacher> findAll();

    Teacher addTeacher(TeacherDto teacherDto);

    Teacher findByEmail(String email) throws TeacherDoesNotExistException;

    Teacher updateRecords(Long id, JsonPatch teacher) throws JsonProcessingException, JsonPatchException, TeacherLogicException;

    void delete(Long id);
    void delete(Teacher teacher);

}