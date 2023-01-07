package com.udacity.jwdnd.course1.cloudstorage.controllers;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.HOME_PAGE;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.HomeService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

	private final UserService userService;
	private final NoteService noteService;
	private final HomeService homeService;

	@PostMapping()
	public String createUpdateNote(Authentication authentication, Model model, Note note) {

		Integer userId = userService.getUserId(authentication.getName());
		note.setUserId(userId);

		int success = 0;

		if (note.getNoteId() == null) {
			success = noteService.createNote(note);
		} else {
			success = noteService.updateNote(note);
		}

		homeService.updatePage(authentication, model);
		return HOME_PAGE;
	}

	@GetMapping("delete/{noteId}")
	public String deleteNote(Authentication authentication, Model model, @PathVariable Integer noteId) {

		int success = noteService.deleteNote(noteId);

		Integer userId = userService.getUserId(authentication.getName());
		homeService.updatePage(authentication, model);

		return HOME_PAGE;
	}
}
