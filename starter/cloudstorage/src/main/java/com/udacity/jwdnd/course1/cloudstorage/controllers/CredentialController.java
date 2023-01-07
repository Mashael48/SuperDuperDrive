package com.udacity.jwdnd.course1.cloudstorage.controllers;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.HOME_PAGE;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/credential")
@RequiredArgsConstructor
public class CredentialController {
	private final CredentialService credentialService;
	private final UserService userService;

	@PostMapping()
	public String createUpdateCredential(Authentication authentication, Model model, Credential credential) {

		Integer userId = userService.getUserId(authentication.getName());
		credential.setUserId(userId);

		int success = 0;

		if (credential.getCredentialId() == null) {
			success = credentialService.createCredential(credential);
		}

		updatecredentialsList(model, userId);
		return HOME_PAGE;
	}

	private void updatecredentialsList(Model model, Integer userId) {
		model.addAttribute("credentialsList", credentialService.getCredentialsList(userId));
	}
}
