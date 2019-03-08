package com.kh.myapp.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.myapp.authority.dto.AuthorityDTO;
import com.kh.myapp.authority.svc.AuthoritySvc;
import com.kh.myapp.member.dao.MemberDAO;
import com.kh.myapp.member.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberSvcImpl implements MemberSvc {

	@Inject 		
	
	@Qualifier("memberDAOImplXML") //동일타입이 있을경우 구분자로 구현클래스 이름을 지정함. ex)Repository("name") 인스턴스 이름을 지정할 수 있다.

	MemberDAO mdao;

	@Inject
	AuthoritySvc asvc;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	// 회원 등록
	@Override
	public boolean insert(MemberDTO memberDTO) {
		boolean success = false;
		
		
		//사용자의 실제 비밀번호를 Bcrypt로 암호화함
		memberDTO.setPw(passwordEncoder.encode(memberDTO.getPw()));
		success = mdao.insert(memberDTO);
		log.info("아이디: "+memberDTO.getId());
		if(success) {
			AuthorityDTO authority = new AuthorityDTO();
			authority.setId(memberDTO.getId());
			authority.setRoleId("ROLE_USER");
			success = asvc.insert(authority);
			log.info("authority: " + authority);
		}
		
		return success;
	}

	// 회원 수정
	@Override
	public boolean modify(MemberDTO memberDTO) {
		boolean success = false;
		success = mdao.modify(memberDTO);
		return success;

	}
	
	// 비밀번호 수정
	@Override
	public boolean pwmodify(MemberDTO memberDTO) {
		boolean success = false;
		success = mdao.pwmodify(memberDTO);
		return success;
	}

	//회원 삭제(회원용)
	@Override
	public boolean delete(String id, String pw) {
		boolean success = false;
		success = mdao.delete(id, pw);
		return success;

	}

	//회원 삭제(관리자용)
	@Override
	public boolean adminDelete(String id) {
		boolean success = false;
		success = mdao.adminDelete(id);
		return success;
	}
	
	// 회원 조회
	@Override
	public MemberDTO getMember(String id) {
		MemberDTO memberDTO = null;	
		
		memberDTO = mdao.getMember(id);
		return memberDTO;
	}

	// 회원 목록 조회
	@Override
	public List<MemberDTO> getMemberList() {
		List<MemberDTO> list = null;
		list = mdao.getMemberList();
		return list;
	}
	
	//아이디찾기
	@Override
	public MemberDTO findID(String tel, String birth) {
		MemberDTO memberDTO = null;	
		memberDTO = mdao.findID(tel, birth);
		return memberDTO;
	}

	//비번찾기
	@Override
	public MemberDTO findPW(String id, String tel, String birth) {
		MemberDTO memberDTO = null;
		memberDTO = mdao.findPW(id, tel, birth);
		return memberDTO;
	}

	//조회
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberDTO mdto = mdao.getMember(id);
		if (mdto == null) {
			throw new UsernameNotFoundException("Invalid username/password.");
		}
		
		List<AuthorityDTO> authorities = asvc.selectOne(mdto.getId());
		mdto.setAuthorities(authorities);
		log.info("msi_mdto = " + mdto);
		log.info("msi_mdto = " + mdto.getUsername());
		log.info("msi_mdto = " + id);
		
		return mdto;
	}





}
