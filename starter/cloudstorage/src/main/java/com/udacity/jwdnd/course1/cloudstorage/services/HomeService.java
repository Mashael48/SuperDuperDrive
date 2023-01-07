package com.udacity.jwdnd.course1.cloudstorage.services;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.CREDENTIALS_LIST;
import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.CREDENTIAL_SERVICE;
import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.NOTES_LIST;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {
	private final UserService userService;
	private final NoteService noteService;
	private final CredentialService credentialService;

	public void updatePage(Authentication authentication, Model model) {

		Integer userId = userService.getUserId(authentication.getName());
		model.addAttribute(CREDENTIAL_SERVICE, credentialService);

		// Initialize all user's lists
		model.addAttribute(NOTES_LIST, noteService.getNotesList(userId));
		model.addAttribute(CREDENTIALS_LIST, credentialService.getCredentialsList(userId));
	}
}
