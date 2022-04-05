package com.example.School_management_System.data.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
public class Test {
    @Id
    private Long id;
    private Course test;
    private int grade;
}
