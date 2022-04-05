package com.example.School_management_System.service;

import com.example.School_management_System.data.models.Teacher;
import com.example.School_management_System.data.repositories.TeacherRepository;
import com.example.School_management_System.dtos.TeacherDto;
import com.example.School_management_System.exceptions.TeacherDoesNotExistException;
import com.example.School_management_System.exceptions.TeacherLogicException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAll() {

        return teacherRepository.findAll();
    }

    @Override
    public Teacher addTeacher(TeacherDto teacherDto) {

        if (teacherDto == null) {
            throw new IllegalArgumentException("teacher requirement not met");
        }

        Optional<Teacher> queryResult = teacherRepository.findByEmail(teacherDto.getEmail());

        if (queryResult.isPresent()) {
            return queryResult.get();
        }

        Teacher teacher = new Teacher();

        teacher.setName(teacherDto.getName());
        teacher.setGender(teacherDto.getGender());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setMobile_number(teacherDto.getMobile());

        return teacherRepository.save(teacher);
    }

    private Teacher saveOrUpdate(Teacher teacher) throws TeacherLogicException {
        if (teacher == null) {
            throw new TeacherLogicException("teacher cannot be null");
        }

        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher findByEmail(String email) throws TeacherDoesNotExistException {

        if (email == null) {
            throw new IllegalArgumentException("argument not found");
        }

        Optional<Teacher> query = teacherRepository.findByEmail(email);

        if (query.isPresent()) {
            return query.get();
        }
        throw new TeacherDoesNotExistException("teacher with this email" + email + "not found");
    }

    @Override
    public Teacher updateRecords(Long id, JsonPatch teacherPatch) throws JsonProcessingException, JsonPatchException, TeacherLogicException {

        Optional<Teacher> teacherQuery = teacherRepository.findById(id);

        if (teacherQuery.isEmpty()) {
            throw new IllegalArgumentException("teacher with  id " + id + "does not exist");
        }

        Teacher teacher1 = teacherQuery.get();

        try {
            teacher1 = applyPatchToBook(teacherPatch, teacher1);
            return saveOrUpdate(teacher1);
        } catch (JsonPatchException | JsonProcessingException | TeacherLogicException je) {
            throw new TeacherLogicException("update failed");

        }
    }

    private Teacher applyPatchToBook(JsonPatch teacherPatch, Teacher teacher1) throws JsonPatchException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode patched = teacherPatch.apply(objectMapper.convertValue(teacher1, JsonNode.class));

        return objectMapper.treeToValue(patched, Teacher.class);
    }





    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);

    }

    @Override
    public void delete(Teacher teacher) {

        teacherRepository.delete(teacher);

    }
}