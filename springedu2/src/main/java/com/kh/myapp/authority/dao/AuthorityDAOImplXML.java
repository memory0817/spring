package com.kh.myapp.authority.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.myapp.authority.dto.AuthorityDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository(value="authorityDAOImplXML")
public class AuthorityDAOImplXML implements AuthorityDAO {

	@Inject
	private SqlSession sql;
	
	//사용자권한등록
	@Override
	public boolean insert(AuthorityDTO authorityDTO) {
		boolean success = false;
		int cnt = sql.insert("mappers.authority.insert", authorityDTO);
		if(cnt > 0) success = true;
		return success;
	}

	//사용자권한수정
	@Override
	public boolean update(AuthorityDTO authorityDTO) {
		boolean success = false;
		int cnt = sql.update("mappers.authority.update", authorityDTO);
		if(cnt > 0) success = true;
		return success;
	}

	//사용자권한삭제
	@Override
	public boolean delete(AuthorityDTO authorityDTO) {
		boolean success = false;
		int cnt = sql.delete("mappers.authority.delete", authorityDTO.getId());
		if(cnt > 0) success = true;
		return success;
	}

	//사용자권한조회
	@Override
	public List<AuthorityDTO> selectOne(String id) {
		List<AuthorityDTO> list = null;
		list = sql.selectList("mappers.authority.selectOne", id);
		
		return list;
	}

	//사용자전체권한 조회
	@Override
	public List<AuthorityDTO> list() {		
		return sql.selectList("mappers.authority.list");
	}

}
