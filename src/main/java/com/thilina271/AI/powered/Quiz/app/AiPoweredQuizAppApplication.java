package com.thilina271.AI.powered.Quiz.app;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class AiPoweredQuizAppApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
		System.setProperty("OPENAI_API_KEY", Objects.requireNonNull(dotenv.get("OPENAI_API_KEY")));

		SpringApplication.run(AiPoweredQuizAppApplication.class, args);
	}
}
