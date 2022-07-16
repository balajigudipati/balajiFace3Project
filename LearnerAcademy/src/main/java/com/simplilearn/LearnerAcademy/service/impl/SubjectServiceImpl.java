package com.simplilearn.LearnerAcademy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.simplilearn.LearnerAcademy.entity.Subject;
import com.simplilearn.LearnerAcademy.exception.NotFoundException;
import com.simplilearn.LearnerAcademy.repository.SubjectRepository;
import com.simplilearn.LearnerAcademy.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	SubjectRepository subjectRepository;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public List<Subject> findAllSubjects() {
		
		return subjectRepository.findAll();	
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	public Subject findSubjectById(Long id) {
		
		return subjectRepository.findById(id)
				.orElseThrow( ()->new NotFoundException(String.format("Course not found  with ID %d", id)));
	}

	@Override
	public void createSubject(Subject subject) {
		subjectRepository.save(subject);
		
	}

	@Override
	public void updateSubject(Subject subject) {
		subjectRepository.save(subject);
		
	}

	@Override
	public void deleteSubject(Long id) {
	var subject=	subjectRepository.findById(id)
		.orElseThrow( ()->new NotFoundException(String.format("Course not found  with ID %d", id)));

		subjectRepository.deleteById(subject.getId());
	}

}
