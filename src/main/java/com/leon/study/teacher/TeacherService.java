package com.leon.study.teacher;

import java.util.Collection;

interface TeacherService {

    Collection<Teacher> findAll();

    Teacher findOne(Long id);

    Teacher createOrUpdateTeacher(Teacher teacher);

    void deleteTeacher(Long id);

}
