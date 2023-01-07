package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserAccessManagementController {

	private final UserService userService;

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signupUser(@ModelAttribute User user, Model model) {

		String signupError = null;

		if (!userService.isUsernameAvailable(user.getUsername())) {
			signupError = "The username already exists.";

		} else {
			int rowsAdded = userService.createUser(user);
			if (rowsAdded < 0) {
				signupError = "There was an error signing you up. Please try again.";
			}
		}

		if (signupError == null) {
			model.addAttribute("signupSuccess", true);
		} else {
			model.addAttribute("signupError", signupError);
		}

		return "signup";
	}

	@GetMapping("/logout")
	public String logout() {
		return "login";
	}

	@GetMapping("/home")
	public String homePage() {
		return "home";
	}
}
