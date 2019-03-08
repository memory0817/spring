package com.kh.myapp.rboard;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.myapp.bbs.dao.RbbsDAO;
import com.kh.myapp.bbs.dto.RbbsDTO;
import com.kh.myapp.bbs.service.RbbsSvc;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class RboardTest2 {

	private static Logger logger = LoggerFactory.getLogger(RboardTest2.class);

	@Inject
	RbbsSvc rbbsSvc;
//	RbbsDAO rbbsDAO;
	
	

	
	RbbsDTO rbbsdto;
	
	List<RbbsDTO> list;

	int cnt; // 실행레코드수

	@BeforeEach
	void beforeEach() {
		rbbsdto = new RbbsDTO();
	}

	// 댓글쓰기
	@Test @Disabled
	void write() {

		rbbsdto.setBnum(10142);
		rbbsdto.setRid("test12@test.com");
		rbbsdto.setRnickname("테스터12");
		rbbsdto.setRcontent("10142댓글테스트222");

		try {
			cnt = rbbsSvc.write(rbbsdto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("댓글처리건수 : " + cnt);

	}

	// 댓글수정
	@Test @Disabled
	void modify() {

		rbbsdto.setRcontent("댓글수정테스트");
		rbbsdto.setRnum(274);

		try {
			cnt = rbbsSvc.modify(rbbsdto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("수정처리건수 : " + cnt);

	}

	// 댓글삭제
	@Test @Disabled
	void delete() {
		
		String rnum = "225";

		try {
			cnt = rbbsSvc.delete(rnum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("삭제처리건수 : " + cnt);

	}	
	
	// 호감비호감
	@Test @Disabled
	void goodorbad() {
		
		String rnum = "274";

		try {
			cnt = rbbsSvc.goodOrBad(rnum, "good");
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("호감비호감 반영건수 : " + cnt);

	}	
	

	// 대댓글등록
	@Test @Disabled
	void reply() {
		
/*		
		rbbsdto.setRnum(100);
		rbbsdto.setBnum(bnum);
		rbbsdto.setRid("test15@test.com");
		rbbsdto.setRnickname("테스터15");
		rbbsdto.setRcontent("114글에대한대댓글");
		rbbsdto.setRgroup(100);
		rbbsdto.setRrdto(rbbsdto);*/
		
		
		//rbbsdto.setRnum(249); //원글번호
		rbbsdto.setBnum(10142); //최초등록글
		rbbsdto.setRid("test12@test.com");
		rbbsdto.setRnickname("테스터13");
		rbbsdto.setRcontent("대댓글 테스트");
		rbbsdto.setRgroup(278); //원글번호 = 원글 그룹
		rbbsdto.setRstep(0+1); //원글 그룹의 세로정렬(답글단계)
		rbbsdto.setRindent(0+1); //원글 그룹의 가로정렬(들여쓰기)
		rbbsdto.setRrnum(278);
		
		try {
			cnt = rbbsSvc.reply(rbbsdto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("대댓글등록 : " + cnt);

	}
	
	//댓글목록보기
	@Test @Disabled
	void list1() {
		
		try {
			list = rbbsSvc.list("10142");
			logger.info("목록건수 : " + list.size());
			for(RbbsDTO rbbsdto : list) {
				logger.info("목록 : " + rbbsdto.toString());	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//댓글목록보기 페이징
	@Test //@Disabled
	void list2() {
		
		try {
			list = rbbsSvc.list("10169", 1, 10);
			logger.info("목록건수 : " + list.size());
			for(RbbsDTO rbbsdto : list) {
				logger.info("목록 : " + rbbsdto.toString());	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//총계
	@Test @Disabled
	void total() {	
		
		try {
			cnt = rbbsSvc.replyTotalRec("10081");
			logger.info("댓글총수 : " + cnt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
