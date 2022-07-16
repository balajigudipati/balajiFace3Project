package com.simplilearn.LearnerAcademy.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;





@Entity
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "roleNumber", length = 50, nullable = false, unique = true)
	private String roleNumber;

	@Column(name = "studentName", length = 100, nullable = false)
	private String studentName;

	@Column(name = "address", length = 50, nullable = false)
	private String address;

	@Column(name = "contact", length = 250, nullable = false)
	private String contact;

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinTable(name = "students_courses", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private Set<Course> courses = new HashSet<Course>();
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "students_subjects", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
			@JoinColumn(name = "subject_id") })
	private Set<Subject> subjects = new HashSet<Subject>();
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "students_teachers", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
			@JoinColumn(name = "teacher_id") })
	private Set<Teacher> teachers = new HashSet<Teacher>();


	public Student(String roleNumber, String studentName, String address, String contact) {
		
		this.roleNumber = roleNumber;
		this.studentName = studentName;
		this.address = address;
		this.contact = contact;
	}


	public Student() {
		super();
		
	}
	
	
	
	public void addCourses(Course course) {
		this.courses.add(course);
		course.getStudents().add(this);
	}
	
	public void removeCourses(Course course) {
		this.courses.remove(course);
		course.getStudents().remove(this);
	}
	
	
	public void addSubjects(Subject subject) {
		this.subjects.add(subject);
		subject.getStudents().add(this);
	}

	public void removeSubjects(Subject subject) {
		this.subjects.remove(subject);
		subject.getStudents().remove(this);
	}

	public void addTeachers(Teacher teacher) {
		this.teachers.add(teacher);
		teacher.getStudents().add(this);
	}

	public void removeTeachers(Teacher teacher) {
		this.teachers.remove(teacher);
		teacher.getStudents().remove(this);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRoleNumber() {
		return roleNumber;
	}


	public void setRoleNumber(String roleNumber) {
		this.roleNumber = roleNumber;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public Set<Course> getCourses() {
		return courses;
	}


	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}


	public Set<Subject> getSubjects() {
		return subjects;
	}


	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}


	public Set<Teacher> getTeachers() {
		return teachers;
	}


	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	
	
	
	
}
