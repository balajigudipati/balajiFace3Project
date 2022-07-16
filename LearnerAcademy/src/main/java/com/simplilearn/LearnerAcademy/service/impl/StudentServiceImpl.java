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

import com.simplilearn.LearnerAcademy.entity.Student;
import com.simplilearn.LearnerAcademy.exception.NotFoundException;
import com.simplilearn.LearnerAcademy.repository.StudentRepository;
import com.simplilearn.LearnerAcademy.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	
	@Autowired
	StudentRepository studentRepository;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Student> findAllStudents() {
		
		return studentRepository.findAll() ;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Student> searchStudents(String keyword) {
		if(keyword != null) {
			return studentRepository.search(keyword);
			
		}
		
		return studentRepository.findAll();
	}

	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Student findStudentById(Long id) {
		
		return studentRepository.findById(id)
				.orElseThrow( ()->new NotFoundException(String.format("Student not found with ID %d", id)));
	}

	@Override
	public void createStudent(Student student) {
		studentRepository.save(student);
		
	}

	@Override
	public void updateStudent(Student student) {
		studentRepository.save(student);
		
	}

	@Override
	public void deleteStudent(Long id) {
	var student=studentRepository.findById(id)
		.orElseThrow( ()->new NotFoundException(String.format("Book not found with ID %d", id)));
		
	studentRepository.delete(student);
	}

	@Override
	public Page<Student> findPaginated(Pageable pageable) {
		var pageSize = pageable.getPageSize();
		var currentPage = pageable.getPageNumber();
		var startItem = currentPage * pageSize;
		List<Student> list;

		if (findAllStudents().size() < startItem) {
			list = Collections.emptyList();
		} else {
			var toIndex = Math.min(startItem + pageSize, findAllStudents().size());
			list = findAllStudents().subList(startItem, toIndex);
		}

		var studentPage = new PageImpl<Student>(list, PageRequest.of(currentPage, pageSize), findAllStudents().size());

		return studentPage;
	}

}
