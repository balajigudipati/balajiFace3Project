package com.simplilearn.LearnerAcademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.LearnerAcademy.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {

}
