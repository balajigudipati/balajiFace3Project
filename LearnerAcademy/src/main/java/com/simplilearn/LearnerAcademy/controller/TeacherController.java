package com.simplilearn.LearnerAcademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.simplilearn.LearnerAcademy.entity.Teacher;
import com.simplilearn.LearnerAcademy.service.TeacherService;

@Controller
public class TeacherController {

	
	@Autowired
	TeacherService teacherService;
	
	@RequestMapping("/teachers")
	public String findAllSubjects(Model model) {

		model.addAttribute("teachers", teacherService.fiindAllTeachers());
		return "list-teachers";
	}

	@RequestMapping("/teacher/{id}")
	public String findTeacherById(@PathVariable("id") Long id, Model model) {

		model.addAttribute("teacher", teacherService.findTeacherById(id));
		return "list-teacher";
	}

	@GetMapping("/addTeacher")
	public String showCreateForm(Teacher teacher) {
		return "add-teacher";
	}

	@RequestMapping("/add-teacher")
	public String createTeacher(Teacher teacher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-teacher";
		}

		teacherService.createTeacher(teacher);
		model.addAttribute("teacher", teacherService.fiindAllTeachers());
		return "redirect:/teachers";
	}

	@GetMapping("/updateTeacher/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		model.addAttribute("teacher", teacherService.findTeacherById(id));
		return "update-teacher";
	}

	@RequestMapping("/update-teacher/{id}")
	public String updateTeacher(@PathVariable("id") Long id,Teacher teacher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			teacher.setId(id);
			return "update-teachers";
		}

		teacherService.updateTeacher(teacher);
		model.addAttribute("teacher", teacherService.fiindAllTeachers());
		return "redirect:/teachers";
	}

	@RequestMapping("/remove-teacher/{id}")
	public String deleteTeacher(@PathVariable("id") Long id, Model model) {
		teacherService.deleteTeacher(id);

		model.addAttribute("teacher", teacherService.fiindAllTeachers());
		return "redirect:/teachers";
	}
}
