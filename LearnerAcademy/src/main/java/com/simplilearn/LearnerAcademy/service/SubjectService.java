package com.simplilearn.LearnerAcademy.service;

import java.util.List;

import com.simplilearn.LearnerAcademy.entity.Subject;

public interface SubjectService {
	
	public List<Subject> findAllSubjects();
	
	public Subject findSubjectById(Long id);
	
	public void createSubject(Subject subject);
	
	public void updateSubject(Subject subject);
	
	public void deleteSubject(Long id);
}
