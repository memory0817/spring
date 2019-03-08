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

import com.kh.myapp.bbs.dto.RbbsDTO;
import com.kh.myapp.bbs.service.RbbsSvc;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class RboardTest {

	private static Logger logger = LoggerFactory.getLogger(RboardTest.class);

	@Inject
	RbbsSvc rbbsSvc;
	
	RbbsDTO rbbsdto;
	
	List<RbbsDTO> list;

	int cnt; // 실행레코드수

	@BeforeEach
	void beforeEach() {
		rbbsdto = new RbbsDTO();
	}

	// 댓글쓰기
	@Test
	@Disabled
	void write() {

		rbbsdto.setBnum(10081);
		rbbsdto.setRid("test12@test.com");
		rbbsdto.setRnickname("테스터13");
		rbbsdto.setRcontent("10081댓글테스트");

		try {
			cnt = rbbsSvc.write(rbbsdto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("처리건수 : " + cnt);

	}

	// 댓글수정
	@Test @Disabled
	void modify() {

		rbbsdto.setRcontent("119댓글수정테스트");
		rbbsdto.setRnum(119);

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
		
		String rnum = "115";

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
		
		String rnum = "115";

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
		int bnum = 10081;
		
		rbbsdto.setBnum(bnum);
		rbbsdto.setRnum(100);
		rbbsdto.setRid("test15@test.com");
		rbbsdto.setRnickname("테스터15");
		rbbsdto.setRcontent("114글에대한대댓글");
		rbbsdto.setRgroup(127);
		rbbsdto.setRrdto(rbbsdto);

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
			list = rbbsSvc.list("10081");
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
			list = rbbsSvc.list("10081", 1, 10);
			logger.info("목록건수 : " + list.size());
			for(RbbsDTO rbbsdto : list) {
				logger.info("목록 : " + rbbsdto.toString());	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test @Disabled
	void totla() {
		
		
		
		try {
			cnt = rbbsSvc.replyTotalRec("10081");
			logger.info("댓글총수 : " + cnt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
