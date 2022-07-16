package com.simplilearn.LearnerAcademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.LearnerAcademy.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
