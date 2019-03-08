package com.kh.myapp;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.myapp.member.dao.MemberDAOImplXML;
import com.kh.myapp.member.dto.MemberDTO;
import com.kh.myapp.member.service.MemberSvcImpl;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
class MemberXmlTest {
	
	private static final Logger logger 
	= LoggerFactory.getLogger(MemberXmlTest.class);
	
	
	@Inject
	MemberDAOImplXML xml;
//	SqlSession sqlSession;
	
	
	//회원가입
	@Test @Disabled
	void test() {
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("test42@test.com");
		memberDTO.setPw("1234");
		memberDTO.setTel("010-1234-5678");
		memberDTO.setNickName("테스터6");
		memberDTO.setGender("여");
		memberDTO.setRegion("부산");
		memberDTO.setBirth("2020-01-05");
			
		boolean success = xml.insert(memberDTO);
		logger.info("생성건수:"+ success);
			
		}
		
	
	//회원 수정
	@Test  @Disabled
	void Modify() {
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("test42@test.com");
		memberDTO.setPw("1234");
		memberDTO.setTel("010-9999-9999");
		memberDTO.setNickName("수정테스터6");
		memberDTO.setGender("여");
		memberDTO.setRegion("부산");
		memberDTO.setBirth("2020-01-05");
		
		
		boolean success = xml.modify(memberDTO);		
		logger.info("회원수정:"+success);		
		
	}
	
	//회원 삭제
	@Test  @Disabled
	void delete() {
		boolean success = xml.delete("test42@test.com","1234");
		logger.info("회원삭제:"+success);	
	}
	
	//회원 삭제 (관리자)
	@Test  @Disabled
	void adminDelete() {
		boolean success = xml.adminDelete("test42@test.com");
		logger.info("회원삭제관리자:"+success);	
	}
	
	
	//회원 조회
	@Test //@Disabled
	void getMember() {
		MemberDTO mdto  = xml.getMember("test52@test.com");
	 logger.info(mdto.toString());
		
	}
	
	
	//회원 목록조회
	@Test @Disabled
	void getMemberList() {
	 List<MemberDTO> list = null;
	 list = xml.getMemberList();
	 list.stream().forEach(m->{logger.info(m.toString());});
	}
	
	
		
	}


