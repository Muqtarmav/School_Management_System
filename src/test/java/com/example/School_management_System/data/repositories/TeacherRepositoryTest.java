package com.example.School_management_System.data.repositories;

import com.example.School_management_System.data.models.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@Sql(scripts = {"/db/insert.sql"})
@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    TeacherRepository teacherRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Teacher can Be saved in database")
    void teacherCanBeSavedInDatabaseTest(){

        Teacher teacher = new Teacher();
        teacher.setName("segun kogi");
        teacher.setGender("male");
        teacher.setEmail("segun@gmail.com");
        teacher.setMobile_number("081456789");

        assertThat(teacher.getId()).isNull();
        log.info("teacher can be saved :: {}", teacher);
        teacherRepository.save(teacher);

        assertThat(teacher.getId()).isNotNull();
        assertThat(teacher.getName()).isEqualTo("segun kogi");
        assertThat(teacher.getGender()).isEqualTo("male");
        assertThat(teacher.getEmail()).isEqualTo("segun@gmail.com");
        assertThat(teacher.getMobile_number()).isEqualTo("081456789");


    }

    @Test
    @DisplayName("Find all teachers in database")
    void allTeachersCanBeFoundInDatabase(){

        List<Teacher> teachers = teacherRepository.findAll();
        assertThat(teachers).isNotNull();
        assertThat(teachers.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("find teacher by Id")
    void teacherCanBeFindById(){

        Teacher teacher = teacherRepository.findById(2L).orElse(null);

        assertThat(teacher).isNotNull();
        assertThat(teacher.getId()).isEqualTo(2);
        assertThat(teacher.getName()).isEqualTo("dbanj oganla");
        assertThat(teacher.getEmail()).isEqualTo("oganla@yahoo.com");
        assertThat(teacher.getGender()).isEqualTo("male");
        assertThat(teacher.getMobile_number()).isEqualTo("0703456789");

        log.info("teacher retrieved :: {}", teacher);
    }

    @Test
    @DisplayName("teacher records can be updated")
    void teacherEmailCanBeUpdated(){

        Teacher teacher = teacherRepository.findByEmail("oganla@yahoo.com").orElse(null);

        assertThat(teacher).isNotNull();
        assertThat(teacher.getEmail()).isEqualTo("oganla@yahoo.com");
        assertThat(teacher.getName()).isEqualTo("dbanj oganla");

        teacher.setEmail("dbanj@gmail.com");

        teacherRepository.save(teacher);

        log.info("teacher record has been updated ::{}", teacher);
        assertThat(teacher.getName()).isEqualTo("dbanj oganla");
        assertThat(teacher.getEmail()).isEqualTo("dbanj@gmail.com");


    }

}