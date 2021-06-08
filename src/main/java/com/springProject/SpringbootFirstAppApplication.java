package com.springProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class SpringbootFirstAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootFirstAppApplication.class, args);
	}

	@GetMapping("/")
	public String welcome() {
		return "index";
	}
}
