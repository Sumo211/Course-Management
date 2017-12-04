package com.leon.study.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findById(@Param("id") Long id);

}
