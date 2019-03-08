package com.kh.myapp.member.dao;

import java.util.List;

import com.kh.myapp.member.dto.MemberDTO;


public interface MemberDAO {
	//회원 등록
	public boolean insert(MemberDTO memberDTO);  
		
	//회원 수정
	public boolean modify(MemberDTO memberDTO);
	
	//비밀번호 수정
	public boolean pwmodify(MemberDTO memberDTO);
	
	//회원 삭제(회원용)
	public boolean delete(String id, String pw);
	
	//회원 삭제(관리자용)
	public boolean adminDelete(String id);
	
	//회원 조회
	public MemberDTO getMember(String id);
	
	//회원 목록 조회
	public List<MemberDTO> getMemberList();

	//아이디찾기
	public MemberDTO findID(String tel, String birth);

	//비밀번호 찾기
	public MemberDTO findPW(String id, String tel, String birth);
	

}
