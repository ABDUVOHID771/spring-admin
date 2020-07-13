package com.example.springloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLoanApplication.class, args);
	}

}
