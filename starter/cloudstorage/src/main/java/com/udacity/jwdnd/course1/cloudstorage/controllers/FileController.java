package com.udacity.jwdnd.course1.cloudstorage.controllers;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.HOME_PAGE;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.entities.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.HomeService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/fileUp")
@RequiredArgsConstructor
public class FileController {

	private final UserService userService;
	private final FileService fileService;
	private final HomeService homeService;

	@PostMapping()
	public String uploadFile(Authentication authentication, @ModelAttribute("fileForm") FileForm fileForm,
			Model model) {

		Integer userId = userService.getUserId(authentication.getName());
		log.info("hi {}", fileForm);

		MultipartFile multipartFile = fileForm.getFile();
		String fileName = multipartFile.getOriginalFilename();

		if (!fileService.isFileNameAvailable(fileName)) {
			model.addAttribute("message", "You have tried to add a duplicate file.");

		} else {
			fileService.addFile(multipartFile, userId);
		}

		homeService.updatePage(authentication, model);
		return HOME_PAGE;
	}

	@GetMapping(value = "/{fileId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody byte[] getFile(@PathVariable Integer fileId) {
		File file = fileService.getFile(fileId);
		return file.getFileData();
	}

}
