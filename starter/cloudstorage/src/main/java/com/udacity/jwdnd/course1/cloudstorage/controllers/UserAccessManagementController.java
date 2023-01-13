package com.udacity.jwdnd.course1.cloudstorage.controllers;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserAccessManagementController {

	private final UserService userService;

	@GetMapping("/" + LOGIN_PAGE)
	public String loginPage(Model model, HttpServletRequest request) {

		// Get `signupSuccess` session value, then clear for next request
		HttpSession session = request.getSession();
		boolean signupSuccess = (session.getAttribute(SIGNUP_SUCCESS) != null)
				? (boolean) session.getAttribute(SIGNUP_SUCCESS)
				: false;
		session.setAttribute(SIGNUP_SUCCESS, false);

		model.addAttribute(SIGNUP_SUCCESS, signupSuccess);
		return LOGIN_PAGE;
	}

	@GetMapping("/" + SIGNUP_PAGE)
	public String signupPage() {
		return "signup";
	}

	@PostMapping("/" + SIGNUP_PAGE)
	public String signupUser(@ModelAttribute User user, Model model, HttpServletRequest request) {

		String signupError = null;
		HttpSession session = request.getSession(); // Get session if exist, otherwise create new one

		if (!userService.isUsernameAvailable(user.getUsername())) {
			signupError = DUBLICATE_USERNAME;

		} else {
			int rowsAdded = userService.createUser(user);
			if (rowsAdded < 0) {
				signupError = GENERAL_ERROR_MESSAGE;
			}
		}

		if (signupError != null) {
			session.setAttribute(SIGNUP_SUCCESS, false);
			model.addAttribute(SIGNUP_ERROR, signupError);
			return SIGNUP_PAGE;
		}

		session.setAttribute(SIGNUP_SUCCESS, true);
		return "redirect:" + LOGIN_PAGE; // `redirect: to set the URL path for /login
	}

	@GetMapping("/" + LOGOUT_PAGE)
	public String logout() {
		return LOGIN_PAGE;
	}

}
