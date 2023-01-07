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
import com.udacity.jwdnd.course1.cloudstorage.entities.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

	private final NoteService noteService;
	private final UserService userService;

	@PostMapping()
	public String createUpdateNote(Authentication authentication, Note note, Model model) {
		User user = userService.getUser(authentication.getName());
		note.setUserId(user.getUserId());

		int success = 0;

		if (note.getNoteId() == null) {
			success = noteService.createNote(note);
		} else {
			success = noteService.updateNote(note);
		}

		updateNotesList(model);
		return HOME_PAGE;
	}

	@GetMapping("{noteId}")
	public String deleteNote(@PathVariable Integer noteId, Model model) {
		int success = noteService.deleteNote(noteId);
		updateNotesList(model);
		return HOME_PAGE;
	}

	private void updateNotesList(Model model) {
		model.addAttribute("notesList", noteService.getNotesList());
	}
}
