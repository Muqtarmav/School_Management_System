package com.example.School_management_System.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

}
