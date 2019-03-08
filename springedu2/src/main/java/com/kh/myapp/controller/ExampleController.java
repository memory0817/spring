package com.kh.myapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/example")
public class ExampleController {

	@GetMapping("/all") //void해놓고 return이 없으면 매핑이름의 view.jsp를 찾는다
	public void doAll() {
		log.info("all page");
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/member")
	public void doMember() {
		log.info("member page");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("admin page");
	}
	
}
