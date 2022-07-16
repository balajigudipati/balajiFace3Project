package com.simplilearn.LearnerAcademy.util;



import java.util.List;
import java.util.stream.Collectors;


import com.simplilearn.LearnerAcademy.entity.Course;
import com.simplilearn.LearnerAcademy.entity.Student;
import com.simplilearn.LearnerAcademy.entity.Subject;
import com.simplilearn.LearnerAcademy.entity.Teacher;
import com.simplilearn.LearnerAcademy.vo.CourseRecord;
import com.simplilearn.LearnerAcademy.vo.StudentRecord;
import com.simplilearn.LearnerAcademy.vo.SubjectRecord;
import com.simplilearn.LearnerAcademy.vo.TeacherRecord;



public class Mapper {

	public static List<StudentRecord> studentModelToVo(List<Student> students) {

		return students.stream().map(vo -> {
			var studentVo = new StudentRecord(vo.getId(), vo.getRoleNumber(), vo.getStudentName(), vo.getAddress(),
					vo.getContact());
			return studentVo;
		}).collect(Collectors.toList());
	}

	public static List<CourseRecord> courseModelToVo(List<Course> courses) {

		return courses.stream().map(vo -> {
			var courseVo = new CourseRecord(vo.getId(), vo.getCourseName());
			return courseVo;
		}).collect(Collectors.toList());

	}

	public static List<SubjectRecord> subjectModelToVo(List<Subject> subjects) {

		return subjects.stream().map(vo -> {
			var subjectVo = new SubjectRecord(vo.getId(), vo.getSubjectName());
			return subjectVo;
		}).collect(Collectors.toList());

	}

	public static List<TeacherRecord> teacherModelToVo(List<Teacher> teachers) {

		return teachers.stream().map(vo -> {
			var teacherVo = new TeacherRecord(vo.getId(), vo.getTeacherName());
			return teacherVo;
		}).collect(Collectors.toList());

	}

}

