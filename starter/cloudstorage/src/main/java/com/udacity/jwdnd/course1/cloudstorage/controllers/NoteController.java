package com.udacity.jwdnd.course1.cloudstorage.controllers;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.HOME_PAGE;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.udacity.jwdnd.course1.cloudstorage.entities.Note;
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoteController {

	private final NoteService noteService;
	private final UserService userService;

	@GetMapping("/note")
	public String viewNote() {
		return HOME_PAGE;
	}

	@GetMapping("/notes")
	public String viewNotesList() {
		return HOME_PAGE;
	}

	@PostMapping("/note")
	public String createNote(Authentication authentication, Note note, Model model) {
		User user = userService.getUser(authentication.getName());
		note.setUserId(user.getUserId());

		int rowsAdded = noteService.createNote(note);
		if (rowsAdded < 0) {
			String errMsg = "There was an error craating the note. Please try again."; // TODO
		}
		
        model.addAttribute("notesList", noteService.getNotesList());

		return HOME_PAGE;
	}

	@DeleteMapping("/note")
	public String deleteNote() {
		return HOME_PAGE;
	}
}
