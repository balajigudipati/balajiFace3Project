package com.simplilearn.LearnerAcademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.simplilearn.LearnerAcademy.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>    {
	
	@Query("SELECT b FROM Student b WHERE b.studentName LIKE %?1%" + " OR b.roleNumber LIKE %?1%" + " OR b.address LIKE %?1%")
	public List<Student> search(String keyword);	

}
