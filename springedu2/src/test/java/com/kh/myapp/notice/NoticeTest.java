package com.kh.myapp.notice;

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

import com.kh.myapp.bbs.dto.BbsDTO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class NoticeTest {
	
	private static Logger logger = LoggerFactory.getLogger(NoticeTest.class);
	
	
	@Inject
	NoticeSvc notsvc;
	NoticeDTO notdto;
	List<NoticeDTO> list;
	
	int cnt;
	
	@BeforeEach
	void beforeEach() {
		notdto = new NoticeDTO();
	}
	
	
	
	//글쓰기
	@Test @Disabled
	void write() {	
		
		
		notdto.setNtitle("공지사항0207-1");
		notdto.setNcontent("공지사항0207");
		
		
		try {
			cnt = notsvc.write(notdto);
			logger.info("레코드 생성건수 : " + cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//글목록
		@Test @Disabled
		void list() {
			try {
				list = notsvc.list();
				logger.info("목록건수 : " + list.size());
				for(NoticeDTO notdto: list) {
					logger.info("목록 : " + notdto.toString());							
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//글목록 페이징
		@Test @Disabled
		void list2() {
			
			try {
				list = notsvc.list(1,5);
				logger.info("목록건수 : " + list.size());
				for(NoticeDTO notdto: list) {
					logger.info("목록 : " + notdto.toString());							
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		//글읽기
		@Test @Disabled
		void view() {
			String nnum = "1";
			try {
				notdto = notsvc.view(nnum);
				logger.info(nnum + "번글내용:" + notdto.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		//글수정
		@Test @Disabled
		void Modify() {
			
			int nnum = 2;

			notdto.setNtitle("[제목수정2]공지사항");
			notdto.setNcontent("[내용수정2]공지사항");
			notdto.setNnum(nnum);
			
			try {
				cnt = notsvc.modify(notdto);
				logger.info("레코드 수정건수 : " + cnt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		//글 삭제
		@Test @Disabled
		void delete() {
			String nnum = "4";
			
			try {
				cnt = notsvc.delete(nnum);
				logger.info("레코드 삭제건수 : " + cnt);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		
		//원글가져오기
		@Test @Disabled
		void replyView() {
			String nnum = "3"; //댓글번호
			try {
				notdto = notsvc.replyView(nnum);
				logger.info("원글 번호 : " + notdto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		//답글쓰기
		@Test @Disabled
		void reply() {
			
			int nnum = 3;
			
			notdto.setNtitle("[답글]입니다.");
			notdto.setNcontent("서비스레이어테스트");
			notdto.setNgroup(nnum);
			
			try {
				cnt = notsvc.reply(notdto);
				logger.info("답글생성건수 : " + cnt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//게시글 총계
		@Test @Disabled
		void totalRec() {
			try {
				cnt = notsvc.totalRec();
				logger.info("게시글 총계 : " + cnt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		// 검색목록
		@Test @Disabled
		void search() {
			
			int startRecord = 1;
			int endRecord = 20;
			String searchType = "TC"; //제목 + 내용
	 		String keyword = "0207";
			
			
			try {
				list = notsvc.list(startRecord, endRecord, searchType, keyword);
				for(NoticeDTO notdto : list) {
					logger.info("검색목록 : " + notdto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

}
