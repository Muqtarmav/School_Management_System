package com.example.School_management_System.service;

import com.example.School_management_System.data.models.Student;
import com.example.School_management_System.data.repositories.StudentRepository;
import com.example.School_management_System.dtos.StudentDto;
import com.example.School_management_System.exceptions.StudentDoesNotExistException;
import com.example.School_management_System.exceptions.StudentLogicException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(StudentDto studentDto) throws StudentLogicException {

        if (studentDto == null){
            throw new IllegalArgumentException("argument not found");
        }

        Optional<Student> query = studentRepository.findByFirstName(studentDto.getFirstName());
        if ( query.isPresent()){
            return query.get();
        }

        Student student = new Student();

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail_address(studentDto.getEmail_address());
        student.setGender(studentDto.getGender());
        student.setMobile_number(studentDto.getMobile_number());;

        return studentRepository.save(student);
    }

    private Student saveorUpdate(Student student) throws StudentLogicException{
        if ( student == null){
            throw new StudentLogicException("student cannot be null");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student findStudentById(Long id) throws StudentDoesNotExistException {
        if (id == null){
            throw new IllegalArgumentException("argument cannot be null");
        }

        Optional<Student> result = studentRepository.findById(id);
        if ( result.isPresent()){
            return result.get();
        }
        throw new StudentDoesNotExistException("student with id : "+ id + "does not exist");
    }

    @Override
    public Student updateStudentById(Long Id, JsonPatch studentPatch) throws StudentLogicException {

        Optional<Student> studentQuery = studentRepository.findById(Id);

        if (studentQuery.isEmpty()){
            throw new IllegalArgumentException("student with id" + Id + "Ddoes not exist");
        }

        Student student1 = studentQuery.get();
        try{
            student1  = applyPatchToStudent(studentPatch, student1);
            return saveorUpdate(student1);
        }

        catch(JsonPatchException | JsonProcessingException | StudentLogicException je){
            throw new StudentLogicException("update failed");
        }
    }

        private Student applyPatchToStudent(JsonPatch productPatch, Student student1) throws JsonProcessingException, JsonPatchException {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode patched = productPatch.apply(objectMapper.convertValue(student1, JsonNode.class));

            return objectMapper.treeToValue(patched, Student.class);
        }

    @Override
    public void delete(Student student) { studentRepository.delete(student);
    }
}
