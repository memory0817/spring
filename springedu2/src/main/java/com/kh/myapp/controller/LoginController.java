package com.kh.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.myapp.login.LoginCmd;
import com.kh.myapp.login.LoginSvc;
import com.kh.myapp.member.dto.MemberDTO;
import com.kh.myapp.member.service.MemberSvc;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	
//	@Inject
//	LoginSvc loginSvc;
//	
//	@Inject
//	MemberSvc memberSvc;
	
	
	
	
	//로그인
	@RequestMapping("/loginForm")
	public void loginForm(Model model) {
		logger.info("loginForm() 호출됨!");
		model.addAttribute( "login" ,new LoginCmd());

	}
	
/*	@RequestMapping(value="/loginOK", method=RequestMethod.POST)
	public String login(@Valid @ModelAttribute("login") LoginCmd login, BindingResult result, HttpSession session) {
		
		logger.info("String login 호출됨!");
		MemberDTO mdto = null;
		
		if(result.hasErrors()) {
			logger.info(result.toString());
			return "/login/loginForm";
		}
		
		//1)회원 유무체크
		if(loginSvc.isMember(login.getId(), login.getPw())) {
		//2)로그인처리
			mdto = loginSvc.login(login.getId(), login.getPw());
			session.setAttribute("user", mdto );
			logger.info("로그인 처리됨:" + login.getId());
		}else {
			return "forward:/login/loginForm";
		}
		
		
		
		return "redirect:/";
	}*/
	
	
	//로그아웃
/*	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/login/loginForm";
	}*/

}
