package com.leon.study.teacher;

import java.util.Collection;

interface TeacherService {

    Collection<Teacher> findAll();

    Teacher findOne(Long id);

    Teacher createTeacher(Teacher teacher);

    Teacher updateTeacher(Long id, Teacher teacher);

    void deleteTeacher(Long id);

}
