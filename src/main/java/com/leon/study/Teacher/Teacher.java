package com.leon.study.Teacher;

import com.leon.study.course.Course;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public Teacher() {
    }

}
