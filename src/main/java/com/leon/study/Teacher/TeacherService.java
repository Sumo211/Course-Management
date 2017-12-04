package com.leon.study.Teacher;

import java.util.Collection;

interface TeacherService {

    Collection<Teacher> findAll();

    Teacher findOne(Long id);

    Teacher createTeacher(Teacher teacher);

    Teacher updateTeacher(Long id, Teacher teacher);

    void deleteTeacher(Long id);

}
