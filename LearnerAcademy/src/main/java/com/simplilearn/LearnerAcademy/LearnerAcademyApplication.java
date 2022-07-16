package com.simplilearn.LearnerAcademy;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.simplilearn.LearnerAcademy.entity.Role;
import com.simplilearn.LearnerAcademy.entity.User;
import com.simplilearn.LearnerAcademy.repository.UserRepository;

@SpringBootApplication
public class LearnerAcademyApplication {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LearnerAcademyApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initialCreate() {
		return (args) -> {
			
			var user = new User("admin", "admin", "admin@admin.in", passwordEncoder.encode("Temp123"),
					Arrays.asList(new Role("ROLE_ADMIN")));
			userRepository.save(user);

		};
	}
	
}
		


