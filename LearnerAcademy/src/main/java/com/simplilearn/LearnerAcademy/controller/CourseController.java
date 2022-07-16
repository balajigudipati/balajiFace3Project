package com.simplilearn.LearnerAcademy.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplilearn.LearnerAcademy.entity.Course;
import com.simplilearn.LearnerAcademy.service.CourseService;

@Controller
public class CourseController {

	
	@Autowired
	CourseService courseService;
	
	
	@RequestMapping("/courses")
	public String findAllCourses(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		var currentPage = page.orElse(1);
		var pageSize = size.orElse(5);
		var bookPage = courseService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("courses", bookPage);

		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			var pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "list-courses";
	}
	
	@RequestMapping("/course/{id}")
	public String findCourseById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("course", courseService.findCourseById(id));
		return "list-course";
	}

	@GetMapping("/addCourse")
	public String showCreateForm(Course course) {
		return "add-course";
	}
	@RequestMapping("/add-course")
	public String createCourse(Course course, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-course";
		}

		courseService.createCourse(course);
		model.addAttribute("course", courseService.findAllCourses());
		return "redirect:/courses";
	}

	@GetMapping("/updateCourse/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		model.addAttribute("course", courseService.findCourseById(id));
		return "update-course";
	}
	@RequestMapping("/update-course/{id}")
	public String updateCourse(@PathVariable("id") Long id, Course course, BindingResult result, Model model) {
		if (result.hasErrors()) {
			course.setId(id);
			return "update-course";
		}

		courseService.updateCourse(course);
		model.addAttribute("course", courseService.findAllCourses());
		return "redirect:/courses";
	}
	
	@RequestMapping("/remove-course/{id}")
	public String deleteCourse(@PathVariable("id") Long id, Model model) {
		courseService.deleteCourse(id);

		model.addAttribute("course", courseService.findAllCourses());
		return "redirect:/courses";
	}
}
