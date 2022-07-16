package com.simplilearn.LearnerAcademy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplilearn.LearnerAcademy.entity.Course;

public interface CourseService {
	
	
	public List<Course> findAllCourses();
	
	public Course findCourseById(Long id);
	
	public void createCourse(Course course);
	
	public void updateCourse(Course course);
	
	public void deleteCourse(Long id);
	
	public Page<Course> findPaginated(Pageable pageable);


}
