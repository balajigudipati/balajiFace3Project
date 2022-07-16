package com.simplilearn.LearnerAcademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.LearnerAcademy.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>   {

}
