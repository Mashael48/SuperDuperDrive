package com.udacity.jwdnd.course1.cloudstorage.controllers;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.HOME_PAGE;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final NoteService noteService;
	private final UserService userService;

	@GetMapping("/" + HOME_PAGE)
	public String homePage(Authentication authentication, Model model) {

		Integer userId = userService.getUserId(authentication.getName());

		// Initialize all user's lists
		model.addAttribute("notesList", noteService.getNotesList(userId));

		return HOME_PAGE;
	}
}
