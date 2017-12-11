package com.leon.study.teacher;

import com.leon.study.exception.ResourceNotFoundException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
@CacheConfig(cacheNames = "teachers")
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    @Cacheable
    public Collection<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    @Cacheable(key = "#a0")
    public Teacher findOne(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Teacher.class, "id", id.toString()));
    }

    @Override
    public Teacher createOrUpdateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    @CacheEvict(key = "#a0", beforeInvocation = true)
    public void deleteTeacher(Long id) {
        Teacher currentTeacher = findOne(id);
        teacherRepository.delete(currentTeacher);
    }

}
