package com.nnk.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String home(Model model) {
		logger.info("Homepage requested");
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		logger.info("Admin Homepage requested");
		return "redirect:/bidList/list";
	}
}
