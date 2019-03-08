package com.kh.myapp.authority.dao;

import java.util.List;

import com.kh.myapp.authority.dto.AuthorityDTO;

public interface AuthorityDAO {
	
	//사용자권한등록
	boolean insert(AuthorityDTO authorityDTO) throws Exception;
	
	//사용자권한수정
	boolean update(AuthorityDTO authorityDTO) throws Exception;
	
	//사용자권한삭제
	boolean delete(AuthorityDTO authorityDTO) throws Exception;
	
	//사용자권한조회
	List<AuthorityDTO> selectOne(String id);
	
	//사용자전체권한 조회
	List<AuthorityDTO> list() throws Exception;

}
