package com.simplilearn.LearnerAcademy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.simplilearn.LearnerAcademy.entity.Teacher;
import com.simplilearn.LearnerAcademy.exception.NotFoundException;
import com.simplilearn.LearnerAcademy.repository.TeacherRepository;
import com.simplilearn.LearnerAcademy.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	TeacherRepository teacherRepository;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Teacher> fiindAllTeachers() {
		
		return teacherRepository.findAll() ;
	}

	@Override
	public Teacher findTeacherById(Long id) {
		
		return teacherRepository.findById(id)
				.orElseThrow(  ()->new NotFoundException(String.format("Teacher not found  with ID %d", id)));
	}

	@Override
	public void createTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
		
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		teacherRepository.save(teacher);
		
	}

	@Override
	public void deleteTeacher(Long id) {
	var teacher=	teacherRepository.findById(id)
		.orElseThrow(  ()->new NotFoundException(String.format("Teacher not found  with ID %d", id)));
		
	teacherRepository.deleteById(teacher.getId());

	}
}