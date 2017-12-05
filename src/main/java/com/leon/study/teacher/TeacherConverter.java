package com.leon.study.teacher;

import org.springframework.stereotype.Component;

@Component
public class TeacherConverter {

    private final TeacherRepository teacherRepository;

    public TeacherConverter(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher toTeacherForCreate(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDTO.getName());
        teacher.setTitle(Title.fromShortName(teacherDTO.getTitle()));
        return teacher;
    }

    public Teacher toTeacherForUpdate(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findOne(id);
        teacher.setName(teacherDTO.getName());
        teacher.setTitle(Title.fromShortName(teacherDTO.getTitle()));
        return teacher;
    }

}
