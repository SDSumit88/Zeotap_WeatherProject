package com.example.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class NavController {

	@GetMapping("/")
	public String redirectToIndex() {
		System.out.println("Redirecting to /index.html");
		return "index"; 
	}
}
