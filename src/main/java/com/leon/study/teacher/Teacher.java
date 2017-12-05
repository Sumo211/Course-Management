package com.leon.study.teacher;

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

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "title", nullable = false, length = 10)
    private Title title;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public Teacher() {
    }

}
