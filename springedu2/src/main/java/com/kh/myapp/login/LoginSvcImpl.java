package com.kh.myapp.login;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kh.myapp.member.dto.MemberDTO;

@Service
public class LoginSvcImpl implements LoginSvc {
	
	@Inject
	LoginDAO loginDAO;

	//회원 유무체크
	@Override
	public boolean isMember(String id, String pw) {
		
		return loginDAO.isMember(id, pw);
	}

	//로그인
	@Override
	public MemberDTO login(String id, String pw) {
		
		return loginDAO.login(id, pw);
	}

}
