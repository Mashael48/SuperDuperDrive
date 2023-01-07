package com.udacity.jwdnd.course1.cloudstorage.controllers;

import static com.udacity.jwdnd.course1.cloudstorage.utils.Constants.HOME_PAGE;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.udacity.jwdnd.course1.cloudstorage.services.HomeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final HomeService homeService;

	@GetMapping("/" + HOME_PAGE)
	public String homePage(Authentication authentication, Model model) {
		homeService.updatePage(authentication, model);
		return HOME_PAGE;
	}
}
