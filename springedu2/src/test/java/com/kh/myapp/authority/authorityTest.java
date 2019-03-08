package com.kh.myapp.authority;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.Digits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.myapp.authority.dto.AuthorityDTO;
import com.kh.myapp.authority.svc.AuthoritySvc;
import com.kh.myapp.member.dto.MemberDTO;
import com.kh.myapp.member.service.MemberSvc;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
class authorityTest {

	
	private static Logger logger = LoggerFactory.getLogger(authorityTest.class);
	
	
	@Inject
	AuthoritySvc authosvc;
	
	AuthorityDTO adto;
	
	List<AuthorityDTO> list;
	
	int cnt;
	
	@BeforeEach
	void beforeEach() {
		adto = new AuthorityDTO();
	}
	
	@Inject
	MemberSvc memSvc;

	//회원 등록
	@Test @Disabled
	void memberInsert() {
		
		boolean success = false;
		
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId("test22@test.com");
			memberDTO.setPw("1234");
			memberDTO.setTel("010-5678-5678");
			memberDTO.setNickName("테스터18");
			memberDTO.setGender("남");
			memberDTO.setRegion("울산");
			memberDTO.setBirth("2011-01-05");
			
			success = memSvc.insert(memberDTO);
				
	}
	
	
	//사용자권한등록
	@Test @Disabled
	void insert() {
		
		adto.setId("test13@test.com");
		adto.setRoleId("ROLE_USER");
		
		authosvc.insert(adto);
		
	}
	
	//사용자권한수정
	@Test @Disabled
	void modify() {
		
		adto.setSeq(2);
		adto.setRoleId("ROLE_ADMIN");
		
		authosvc.update(adto);
		
	}
	
	//사용자권한삭제
	@Test @Disabled
	void delete() {
		
		adto.setRoleId("ROLE_ADMIN");
		
	}
	
	//사용자권한조회
	@Test //@Disabled
	void list() {
		
		authosvc.selectOne("test12@test.com");
		
	}
		


}
