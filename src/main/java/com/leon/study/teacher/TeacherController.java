package com.leon.study.teacher;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    private final TeacherConverter teacherConverter;

    public TeacherController(TeacherService teacherService, TeacherConverter teacherConverter) {
        this.teacherService = teacherService;
        this.teacherConverter = teacherConverter;
    }

    @GetMapping
    public Collection<Teacher> findAll() {
        return teacherService.findAll();
    }

    @GetMapping("/{id}")
    public Teacher findOne(@PathVariable Long id) {
        return teacherService.findOne(id);
    }

    @PostMapping
    public Teacher createTeacher(@Validated(TeacherDTO.New.class) @RequestBody TeacherDTO teacher) {
        Teacher newTeacher = teacherConverter.toTeacherForCreate(teacher);
        return teacherService.createOrUpdateTeacher(newTeacher);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable Long id, @Validated(TeacherDTO.Existing.class) @RequestBody TeacherDTO teacher) {
        Teacher updatedTeacher = teacherConverter.toTeacherForUpdate(id, teacher);
        return teacherService.createOrUpdateTeacher(updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

}
