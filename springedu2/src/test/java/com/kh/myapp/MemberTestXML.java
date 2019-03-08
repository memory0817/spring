package com.kh.myapp;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.myapp.member.dto.MemberDTO;
import com.kh.myapp.member.service.MemberSvc;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class MemberTestXML {

	private static final Logger logger 
		= LoggerFactory.getLogger(MemberTestXML.class);
	
	@Inject
	MemberSvc memSvc;

	//회원 등록
	@Test @Disabled
	void memberInsert() {
		/*logger.info("MemberDAOImplJDBC:"+memberDAOImplJdbc);
		boolean success = false;
		
		for(int i=1;i<=10;i++) {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId("test"+i+"@test.com");
			memberDTO.setPw("1234");
			memberDTO.setTel("010-1234-5678");
			memberDTO.setNickName("테스터_"+i);
			memberDTO.setGender("남");
			memberDTO.setRegion("울산");
			memberDTO.setBirth("2020-01-"+i);
			
			success = memberDAOImplJdbc.insert(memberDTO);
		}
		logger.info("회원등록:"+success);*/
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
	
	//회원 수정
	@Test @Disabled
	void memberModify() {
		boolean success = false;
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("test20@test.com");
		memberDTO.setPw("1234");
		memberDTO.setTel("016-1234-5678");
		memberDTO.setNickName("테스터20");
		memberDTO.setGender("남");
		memberDTO.setRegion("울산");
		memberDTO.setBirth("2022-01-01");
		
		success = memSvc.modify(memberDTO);
		
		logger.info("회원수정:"+success);		
		
	}
	
	//비밀번호 수정
	@Test @Disabled
	void pwmodify() {
		boolean success = false;
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setId("test12@test.com");
		memberDTO.setPw("1234");
		success = memSvc.pwmodify(memberDTO);
		
		logger.info("비번수정:"+success);	
		
	}
	
	
	
	//회원 삭제
	@Test @Disabled
	void delete() {
		boolean success = false;
		success = memSvc.delete("test20@test.com","1234");
		
		logger.info("회원삭제:"+success);	
	}
	//회원 조회
	@Test @Disabled
	void getMember() {
	 MemberDTO mdto = memSvc.getMember("test1@test.com");
	 logger.info("회원조회:"+mdto);
		
	}
	
	//회원 목록조회
	@Test @Disabled
	void getMemberList() {
	 List<MemberDTO> list = memSvc.getMemberList();
	 list.stream().forEach(m->{logger.info(m.toString());});
	}
	
	//회원 목록조회
	@Test @Disabled
	void getMemberList2() {
	 List<MemberDTO> list = memSvc.getMemberList();
	 for(MemberDTO mdto:list) {
		 logger.info(mdto.toString());
	 }
	}
	
	//회원 목록조회
	@Test @Disabled
	void getMemberList3() {
	 List<MemberDTO> list = memSvc.getMemberList();
	 for(int i=0; i< list.size(); i++) {
		 logger.info(list.get(i).toString());
	 }
	}
	
	//아이디 찾기
	@Test @Disabled
	void findid() {
		MemberDTO mdto = memSvc.findID("010-1234-5678", "2020-01-05");
		logger.info("회원조회:"+mdto);
	}
	
	//비밀번호 찾기
	@Test @Disabled
	void findpw() {
		MemberDTO mdto = memSvc.findPW("test12@test.com","010-1234-5678", "2020-01-05");
		logger.info("회원조회:"+mdto);
	}
}









