package com.udacity.jwdnd.course1.cloudstorage.controllers;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.*;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.entities.File;
import com.udacity.jwdnd.course1.cloudstorage.entities.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/" + FILE_PAGE)
@RequiredArgsConstructor
public class FileController {

	private final UserService userService;
	private final FileService fileService;
	private final HomeService homeService;

	@PostMapping()
	public String uploadFile(Authentication authentication, @ModelAttribute("fileForm") FileForm fileForm,
			Model model) {

		Integer userId = userService.getUserId(authentication.getName());

		MultipartFile multipartFile = fileForm.getFile();
		String fileName = multipartFile.getOriginalFilename();

		if (!fileService.isFileNameAvailable(fileName)) {
			model.addAttribute(RESULT_PAGE, ERROR);
			model.addAttribute(MESSAGE, DUBLICATE_FILE_NAME);
		} else {
			int success = fileService.addFile(multipartFile, userId);
			model.addAttribute(RESULT_PAGE, (success > 0) ? SUCCESS : NOT_SAVED);
		}

		homeService.updatePage(authentication, model);
		return RESULT_PAGE;
	}

	@GetMapping(value = "get/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody byte[] getFile(@PathVariable String fileName) {
		File file = fileService.getFile(fileName);
		return file.getFileData();
	}

	@GetMapping(value = "delete/{fileName}")
	public String deleteFile(Authentication authentication, @PathVariable String fileName, Model model) {

		int success = fileService.deleteFile(fileName);
		homeService.updatePage(authentication, model);

		model.addAttribute(RESULT_PAGE, (success > 0) ? SUCCESS : NOT_SAVED);
		return RESULT_PAGE;
	}
}
