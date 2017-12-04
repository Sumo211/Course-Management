package com.leon.study.Teacher;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Collection<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findOne(Long id) {
        return teacherRepository.findOne(id);
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher currentTeacher = findOne(id);
        BeanUtils.copyProperties(teacher, currentTeacher, "id");
        return teacherRepository.save(currentTeacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher currentTeacher = findOne(id);
        teacherRepository.delete(currentTeacher);
    }

}
