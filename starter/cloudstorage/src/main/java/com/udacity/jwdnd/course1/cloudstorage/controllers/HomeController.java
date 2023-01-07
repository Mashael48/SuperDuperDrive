package com.udacity.jwdnd.course1.cloudstorage.controllers;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.HOME_PAGE;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final UserService userService;
	private final NoteService noteService;
	private final CredentialService credentialService;

	@GetMapping("/" + HOME_PAGE)
	public String homePage(Authentication authentication, Model model) {

		Integer userId = userService.getUserId(authentication.getName());
		model.addAttribute("credentialService", credentialService);

		// Initialize all user's lists
		model.addAttribute("notesList", noteService.getNotesList(userId));
		model.addAttribute("credentialsList", credentialService.getCredentialsList(userId));

		return HOME_PAGE;
	}
}
