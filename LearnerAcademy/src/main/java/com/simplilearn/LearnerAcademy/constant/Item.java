package com.simplilearn.LearnerAcademy.constant;



import java.util.Arrays;
import java.util.Optional;

public enum Item {

	STUDENT("all-student", "Student-List.csv"), SUBJECT("all-subject", "Subject-List.csv"),
	TEACHER("all-teacher", "Teacher-List.csv"), COURSE("all-course", "Course-List.csv");

	private final String name;
	private final String fileName;

	Item(String name, String fileName) {
		this.name = name;
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public String getFileName() {
		return fileName;
	}

	public static Optional<Item> getItemByValue(String value) {
		return Arrays.stream(Item.values())
				.filter(accStatus -> accStatus.name.equals(value) || accStatus.fileName.equals(value)).findFirst();
	}
}
