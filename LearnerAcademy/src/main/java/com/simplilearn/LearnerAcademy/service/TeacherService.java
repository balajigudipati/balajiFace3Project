package com.simplilearn.LearnerAcademy.service;

import java.util.List;

import com.simplilearn.LearnerAcademy.entity.Teacher;

public interface TeacherService {

	public List<Teacher> fiindAllTeachers();
	
	public Teacher findTeacherById(Long id);
	
	public void createTeacher(Teacher teacher);
	
	public void updateTeacher(Teacher teacher);
	
	public void deleteTeacher(Long id);
}
