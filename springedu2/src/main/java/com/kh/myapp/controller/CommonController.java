package com.kh.myapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommonController {
	
	@GetMapping("/accessDeny")
	public String accessDeny(Authentication auth, Model model) {
		log.info("accessDeny"+auth);
		model.addAttribute("msg", "접근제한구역입니닷");
		return "/common/forbidden";
		
	}

}
