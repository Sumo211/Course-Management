package com.leon.study.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leon.study.course.Course;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "title", nullable = false, length = 10)
    private Title title;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

}
