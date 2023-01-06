package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signupUser() {
		// TODO: Add user credentials to the embedded database
		return "login";
	}

	@GetMapping("/home")
	public @ResponseBody String homePage() {
		return "HOME!";
	}
}
