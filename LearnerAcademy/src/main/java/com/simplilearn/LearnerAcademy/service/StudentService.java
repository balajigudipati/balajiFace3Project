package com.simplilearn.LearnerAcademy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.simplilearn.LearnerAcademy.entity.Student;

public interface StudentService {
	
	public List<Student> findAllStudents();
	
	public List<Student> searchStudents(String keyword);
	
	public Student findStudentById(Long id);
	
	public void createStudent(Student student);
	
	public void updateStudent(Student student);
	
	public void deleteStudent(Long id);
	
	public Page<Student> findPaginated(Pageable pageable);

}
