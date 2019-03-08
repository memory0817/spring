package com.kh.myapp;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.myapp.login.LoginDAO;
import com.kh.myapp.login.LoginDAOImplJDBC;
import com.kh.myapp.login.LoginSvc;
import com.kh.myapp.member.dto.MemberDTO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class LoginTest {
	
	private static final Logger logger 
	= LoggerFactory.getLogger(MemberXmlTest.class);
	
	@Inject
	LoginDAO loginDAO;
	
	@Inject
	LoginSvc loginSvc;
	
	
	//회원유무(dao레이어)
	@Test //@Disabled
	void isMember() {
		boolean success = loginDAO.isMember("test12@test.com" , "1234");
		logger.info("회원유무여부:" + success);
		
	}
	
	//로그인(dao레이어)
	@Test //@Disabled
	void login() {
		MemberDTO mdto = null;
		mdto = loginDAO.login("test12@test.com","1234");
		logger.info("로그인성공여부:" + mdto);
		
	}
	
	
	//회원유무(서비스레이어)
	@Test //@Disabled
	void isMember2() {
		boolean success = loginSvc.isMember("test12@test.com" , "1234");
		logger.info("회원유무여부:" + success);
		
	}
	
	//로그인(서비스레이어)
	@Test //@Disabled
	void login2() {
		MemberDTO mdto = null;
		mdto = loginSvc.login("test12@test.com","1234");
		logger.info("로그인성공여부:" + mdto);
		
	}

}
