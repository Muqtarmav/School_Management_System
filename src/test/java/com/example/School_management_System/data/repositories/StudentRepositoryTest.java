package com.example.School_management_System.data.repositories;

import com.example.School_management_System.data.models.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@Sql(scripts = {"/db/insert.sql"})
@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("student can be saved in database")
    void studentCanBeSavedInTheDatabaseTest() {

        Student student = new Student();
        student.setFirstName("muqtar");
        student.setLastName("tunji");
        student.setEmail_address("mavic@gmail.com");
        student.setGender("male");
        student.setMobile_number("08130249216");
        //student.setSubjectList()


        assertThat(student.getId()).isNull();
        log.info("student is saved ::{}", student);
        studentRepository.save(student);

        assertThat(student.getId()).isNotNull();
        assertThat(student.getFirstName()).isEqualTo("muqtar");
        assertThat(student.getLastName()).isEqualTo("tunji");
        assertThat(student.getEmail_address()).isEqualTo("mavic@gmail.com");
        assertThat(student.getMobile_number()).isEqualTo("08130249216");

    }

    @Test
    @DisplayName("find all student in database")
    void findAllStudentInTheDatabase() {
        List<Student> student = studentRepository.findAll();

        assertThat(student).isNotNull();
        assertThat(student.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("find student by Id")
    void studentCanByFindByIdTest() {

        Student student = studentRepository.findById(1L).orElse(null);
        assertThat(student).isNotNull();
        assertThat(student.getFirstName()).isEqualTo("muqtar");
        assertThat(student.getLastName()).isEqualTo("tunji");
        assertThat(student.getGender()).isEqualTo("male");
        assertThat(student.getMobile_number()).isEqualTo("08130249216");
        assertThat(student.getEmail_address()).isEqualTo("mavic@gmail.com");
        assertThat(student.getId()).isEqualTo(1);

        log.info("student retrieved ::{}", student);

    }


    @Test
    @DisplayName("update student by email_address")
    void studentEmailCanBeUpdatedTest() {

        Student savedStudent = studentRepository.findByFirstName("muqtar").orElse(null);
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getFirstName()).isEqualTo("muqtar");
        assertThat(savedStudent.getEmail_address()).isEqualTo("mavic@gmail.com");

        savedStudent.setFirstName("muqtar1");
        savedStudent.setEmail_address("mav@gmail.com");


        studentRepository.save(savedStudent);
        assertThat(savedStudent.getFirstName()).isEqualTo("muqtar1");
        assertThat(savedStudent.getEmail_address()).isEqualTo("mav@gmail.com");
    }

}
