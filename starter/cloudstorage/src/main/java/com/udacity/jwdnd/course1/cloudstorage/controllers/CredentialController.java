package com.udacity.jwdnd.course1.cloudstorage.controllers;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.*;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.*;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/" + CREDENTIAL_PAGE)
@RequiredArgsConstructor
public class CredentialController {

	private final UserService userService;
	private final CredentialService credentialService;
	private final HomeService homeService;

	@PostMapping()
	public String createUpdateCredential(Authentication authentication, Model model, Credential credential) {

		Integer userId = userService.getUserId(authentication.getName());
		credential.setUserId(userId);

		int success = 0;
		if (credential.getCredentialId() == null) {
			success = credentialService.createCredential(credential);
		} else {
			success = credentialService.updateCredential(credential);
		}
		model.addAttribute(CREDENTIAL_SERVICE, credentialService);
		homeService.updatePage(authentication, model);

		model.addAttribute(RESULT_PAGE, (success > 0) ? SUCCESS : NOT_SAVED);
		return RESULT_PAGE;
	}

	@GetMapping("delete/{credentialId}")
	public String deleteCredential(Authentication authentication, Model model, @PathVariable Integer credentialId) {

		int success = credentialService.deleteCredential(credentialId);
		homeService.updatePage(authentication, model);

		model.addAttribute(RESULT_PAGE, (success > 0) ? SUCCESS : NOT_SAVED);
		return RESULT_PAGE;
	}
}
