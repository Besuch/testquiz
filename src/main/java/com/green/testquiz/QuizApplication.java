package com.green.testquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.green.testquiz")
public class QuizApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}
}
