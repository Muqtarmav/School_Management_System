package com.example.School_management_System.data.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email_address;
    @Column(nullable = false)
    private String mobile_number;
    @Column(nullable = false)
    private String gender;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_course",
            joinColumns = @JoinColumn(name =  "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name =  "course_id", referencedColumnName = "id"))
    private Set<Course> coursesList;

//     @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     @JoinColumn(name = "id", referencedColumnName = "student_Id")
//    private List<Courses> coursesList = new ArrayList<>();
//

//     public void addCourses(Courses courses) {
//         if (coursesList == null) {
//             coursesList = new ArrayList<>();
//         }
//
//         coursesList.add(courses);
//     }
}


