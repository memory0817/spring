package com.kh.myapp.login;

import com.kh.myapp.member.dto.MemberDTO;


public interface LoginSvc {
	
	
	//회원유무 체크
	public boolean isMember(String id, String pw);
	
	//로그인
	public MemberDTO login(String id, String pw);

}
