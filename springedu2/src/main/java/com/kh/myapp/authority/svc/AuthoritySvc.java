package com.kh.myapp.authority.svc;

import java.util.List;

import com.kh.myapp.authority.dto.AuthorityDTO;

public interface AuthoritySvc {

	//사용자권한등록
	boolean insert(AuthorityDTO authorityDTO);
	
	//사용자권한수정
	boolean update(AuthorityDTO authorityDTO);
	
	//사용자권한삭제
	boolean delete(AuthorityDTO authorityDTO);
	
	//사용자권한조회
	List<AuthorityDTO> selectOne(String id);
	
	//사용자전체권한 조회
	List<AuthorityDTO> list();
	
}
