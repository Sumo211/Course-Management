package com.leon.study.teacher;

import com.leon.study.common.IsCorrectName;
import com.leon.study.course.Course;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "Id does not mandatory", groups = New.class)
    @NotNull(message = "Id cannot be null", groups = Existing.class)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    @IsCorrectName(min = 5, max = 30, groups = {New.class, Existing.class})
    private String name;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public Teacher() {
    }

    interface New {
    }

    interface Existing {

    }

}
