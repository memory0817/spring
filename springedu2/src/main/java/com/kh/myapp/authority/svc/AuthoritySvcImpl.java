package com.kh.myapp.authority.svc;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kh.myapp.authority.dao.AuthorityDAO;
import com.kh.myapp.authority.dto.AuthorityDTO;
import com.kh.myapp.notice.NoticeDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthoritySvcImpl implements AuthoritySvc {

	@Inject
	AuthorityDAO authorityDAO;
	

	
	//사용자권한등록
	@Override
	public boolean insert(AuthorityDTO authorityDTO) {
		log.info("사용자권한등록 insert호출!");
		boolean success = false;
		try {
			success = authorityDAO.insert(authorityDTO);
		} catch (Exception e) {
		}
		log.info("authorityDTO:" + authorityDTO);
		return success;
	}

	//사용자권한수정
	@Override
	public boolean update(AuthorityDTO authorityDTO) {
		boolean success = false;
		try {
			success = authorityDAO.update(authorityDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}

	//사용자권한삭제
	@Override
	public boolean delete(AuthorityDTO authorityDTO) {
		boolean success = false;
		try {
			success = authorityDAO.delete(authorityDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}

	//사용자권한조회
	@Override
	public List<AuthorityDTO> selectOne(String id) {
	
	return 	authorityDAO.selectOne(id);
	}

	//사용자전체권한 조회
	@Override
	public List<AuthorityDTO> list() {
		List<AuthorityDTO> list = null;
		
		try {
			list = authorityDAO.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
