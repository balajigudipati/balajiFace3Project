package com.simplilearn.LearnerAcademy.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplilearn.LearnerAcademy.entity.Student;
import com.simplilearn.LearnerAcademy.service.CourseService;
import com.simplilearn.LearnerAcademy.service.StudentService;
import com.simplilearn.LearnerAcademy.service.SubjectService;
import com.simplilearn.LearnerAcademy.service.TeacherService;

@Controller
public class StudentController {
	
	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	TeacherService teacherService;
	
	
	@RequestMapping({"/", "/students" })
	public String findAllStudents(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		var currentPage = page.orElse(1);
		var pageSize = size.orElse(5);

		var studentPage = studentService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("students", studentPage);

		var totalPages = studentPage.getTotalPages();
		if (totalPages > 0) {
			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list-students";
	}
	
	@RequestMapping("/searchStudent")
	public String searchStudent(@Param("keyword") String keyword, Model model) {

		model.addAttribute("students", studentService.searchStudents(keyword));
		model.addAttribute("keyword", keyword);
		return "list-students";
	}
	
	@RequestMapping("/student/{id}")
	public String findStudentById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("student", studentService.findStudentById(id));
		return "list-student";
	}
	
	@GetMapping("/add")
	public String showCreateForm(Student student, Model model) {

		model.addAttribute("subjects", subjectService.findAllSubjects());
		model.addAttribute("courses", courseService.findAllCourses());
		model.addAttribute("teachers", teacherService.fiindAllTeachers());
		return "add-student";
	}
	
	@RequestMapping("/add-student")
	public String createStudent(Student student, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-student";
		}

		studentService.createStudent(student);
		model.addAttribute("student", studentService.findAllStudents());
		return "redirect:/students";
	}
	
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		model.addAttribute("student", studentService.findStudentById(id));
		return "update-student";
	}
	
	@RequestMapping("/update-student/{id}")
	public String updateStudent(@PathVariable("id") Long id, Student student, BindingResult result, Model model) {
		if (result.hasErrors()) {
			student.setId(id);
			return "update-student";
		}

		studentService.updateStudent(student);
		model.addAttribute("student", studentService.findAllStudents());
		return "redirect:/students";
	}
	
	@RequestMapping("/remove-student/{id}")
	public String deleteStudent(@PathVariable("id") Long id, Model model) {
		studentService.deleteStudent(id);

		model.addAttribute("student", studentService.findAllStudents());
		return "redirect:/students";
	}

	
	
}
