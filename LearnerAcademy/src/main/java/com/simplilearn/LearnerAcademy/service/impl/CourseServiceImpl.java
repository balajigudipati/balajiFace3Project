package com.simplilearn.LearnerAcademy.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.simplilearn.LearnerAcademy.entity.Course;
import com.simplilearn.LearnerAcademy.exception.NotFoundException;
import com.simplilearn.LearnerAcademy.repository.CourseRepository;
import com.simplilearn.LearnerAcademy.service.CourseService;
@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	CourseRepository courseRepository;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Course> findAllCourses() {
		
		return courseRepository.findAll()  ;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Course findCourseById(Long id) {
		return courseRepository.findById(id)
				.orElseThrow( ()->new NotFoundException(String.format("Course not found with ID %d", id)));
	}

	@Override
	public void createCourse(Course course) {
		courseRepository.save(course);
		
	}

	@Override
	public void updateCourse(Course course) {
		courseRepository.save(course);
		
	}

	@Override
	public void deleteCourse(Long id) {
	var course=	courseRepository.findById(id)
		.orElseThrow( ()->new NotFoundException(String.format("Course not found with ID %d", id)));
		
	courseRepository.deleteById(course.getId());  
	}

	@Override
	public Page<Course> findPaginated(Pageable pageable) {
		var pageSize = pageable.getPageSize();
		var coursePage = pageable.getPageNumber();
		var startItem = coursePage * pageSize;
		List<Course> list;

		if (findAllCourses().size() < startItem) {
			list = Collections.emptyList();
		} else {
			var toIndex = Math.min(startItem + pageSize, findAllCourses().size());
			list = findAllCourses().subList(startItem, toIndex);
		}

		return new PageImpl<Course>(list, PageRequest.of(coursePage, pageSize), findAllCourses().size());

	}

}
