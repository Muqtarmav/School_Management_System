package com.example.School_management_System.data.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String mobile_number;
    @Column(nullable = false)
    private String gender;


}
