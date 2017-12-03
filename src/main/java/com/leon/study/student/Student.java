package com.leon.study.student;

import com.leon.study.course.Course;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

    public Student() {
    }

}
