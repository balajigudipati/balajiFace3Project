package com.simplilearn.LearnerAcademy.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.simplilearn.LearnerAcademy.constant.Item;
import com.simplilearn.LearnerAcademy.service.CourseService;
import com.simplilearn.LearnerAcademy.service.StudentService;
import com.simplilearn.LearnerAcademy.service.SubjectService;
import com.simplilearn.LearnerAcademy.service.TeacherService;
import com.simplilearn.LearnerAcademy.util.Mapper;
import com.simplilearn.LearnerAcademy.vo.CourseRecord;
import com.simplilearn.LearnerAcademy.vo.StudentRecord;
import com.simplilearn.LearnerAcademy.vo.SubjectRecord;
import com.simplilearn.LearnerAcademy.vo.TeacherRecord;

@Controller
public class FileExportController {
	
	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	TeacherService teacherService;
	
	@GetMapping("/export/{fileName}")
	public void exportCSV(@PathVariable(value = "fileName") String fileName, HttpServletResponse response)
			throws Exception {

		var item = Item.getItemByValue(fileName);
		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + item.get().getFileName() + "\"");

		switch (item.get()) {
		case STUDENT:
			StatefulBeanToCsv<StudentRecord> writer1 = getWriter(response.getWriter());
			writer1.write(Mapper.studentModelToVo(studentService.findAllStudents()));
			break;
		case COURSE:
			StatefulBeanToCsv<CourseRecord> writer2 = getWriter(response.getWriter());
			writer2.write(Mapper.courseModelToVo(courseService.findAllCourses()));
			break;
		case SUBJECT:
			StatefulBeanToCsv<SubjectRecord> writer3 = getWriter(response.getWriter());
			writer3.write(Mapper.subjectModelToVo(subjectService.findAllSubjects()));
			break;
		case TEACHER:
			StatefulBeanToCsv<TeacherRecord> writer4 = getWriter(response.getWriter());
			writer4.write(Mapper.teacherModelToVo(teacherService.fiindAllTeachers()));
			break;
		}

	}

	private static <T> StatefulBeanToCsv<T> getWriter(PrintWriter printWriter) {
		return new StatefulBeanToCsvBuilder<T>(printWriter).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
				.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false).build();
	}
	
}
