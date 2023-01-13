package com.udacity.jwdnd.course1.cloudstorage.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String loginPage(Model model, HttpServletRequest request) {

		// Get `signupSuccess` session value, then clear for next request
		HttpSession session = request.getSession();
		boolean signupSuccess = (session.getAttribute("signupSuccess") != null)
				? (boolean) session.getAttribute("signupSuccess")
				: false;
		session.setAttribute("signupSuccess", false);

		model.addAttribute("signupSuccess", signupSuccess);
		return "login";
	}

	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signupUser(@ModelAttribute User user, Model model, HttpServletRequest request) {

		String signupError = null;
		HttpSession session = request.getSession(); // Get session if exist, otherwise create new one

		if (!userService.isUsernameAvailable(user.getUsername())) {
			signupError = "The username already exists.";

		} else {
			int rowsAdded = userService.createUser(user);
			if (rowsAdded < 0) {
				signupError = "There was an error signing you up. Please try again.";
			}
		}

		if (signupError != null) {
			session.setAttribute("signupSuccess", false);
			model.addAttribute("signupError", signupError);
			return "signup";
		}

		session.setAttribute("signupSuccess", true);
		return "redirect:login"; // `redirect: to set the URL path for /login
	}

	@GetMapping("/logout")
	public String logout() {
		return "login";
	}

}
