package com.leon.study.teacher;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Collection<Teacher>> findAll() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findOne(@PathVariable Long id, WebRequest webRequest) {
        Teacher teacher = teacherService.findOne(id);
        if (webRequest.checkNotModified(teacher.getVersion())) {
            return null;
        } else {
            return ResponseEntity.ok().eTag(teacher.getVersion().toString()).body(teacherService.findOne(id));
        }
    }

    @PostMapping
    public ResponseEntity<Void> createTeacher(@Validated(TeacherDTO.New.class) @RequestBody TeacherDTO teacher) {
        Teacher newTeacher = teacherConverter.toTeacherForCreate(teacher);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(teacherService.createOrUpdateTeacher(newTeacher).getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @Validated(TeacherDTO.Existing.class) @RequestBody TeacherDTO teacher) {
        Teacher updatedTeacher = teacherConverter.toTeacherForUpdate(id, teacher);
        return ResponseEntity.ok(teacherService.createOrUpdateTeacher(updatedTeacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

}
