package com.simplilearn.LearnerAcademy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplilearn.LearnerAcademy.entity.Subject;
import com.simplilearn.LearnerAcademy.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	SubjectService subjectService;

	@RequestMapping("/subjects")
	public String findAllSubjects(Model model) {

		model.addAttribute("subjects", subjectService.findAllSubjects());
		return "list-subjects";
	}
	
	@GetMapping("/addSubject")
	public String showCreateForm(Subject subject) {
		return "add-subject";
	}
	@RequestMapping("/add-subject")
	public String createSubject(Subject subject, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-subject";
		}

		subjectService.createSubject(subject);
		model.addAttribute("subject", subjectService.findAllSubjects());
		return "redirect:/subjects";
	}
	@GetMapping("/updateSubject/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {

		model.addAttribute("subject", subjectService.findSubjectById(id));
		return "update-subject";
	}
	@RequestMapping("/update-subject/{id}")
	public String updateSubject(@PathVariable("id") Long id, Subject subject, BindingResult result, Model model) {
		if (result.hasErrors()) {
			subject.setId(id);
			return "update-subject";
		}

		subjectService.updateSubject(subject);
		model.addAttribute("subject", subjectService.findAllSubjects());
		return "redirect:/subjects";
	}
	@RequestMapping("/remove-subject/{id}")
	public String deleteSubject(@PathVariable("id") Long id, Model model) {
		subjectService.deleteSubject(id);

		model.addAttribute("subject", subjectService.findAllSubjects());
		return "redirect:/subjects";
	}
}
