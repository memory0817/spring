package com.kh.myapp.notice;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kh.myapp.util.FindCriteria;
import com.kh.myapp.util.PageCriteria;
import com.kh.myapp.util.RecordCriteria;


@Service
public class NoticeSvcImpl implements NoticeSvc {
	
	
	private static Logger logger = LoggerFactory.getLogger(NoticeSvcImpl.class);	

	
	@Inject
	@Qualifier("noticeDAOImplXML")
	
	
	NoticeDAO notdao;
	
	//글쓰기
	@Override
	public int write(NoticeDTO notDTO) throws Exception {
		int cnt = 0;
		cnt = notdao.write(notDTO);
		return cnt;
	}

	//글목록 전체
	@Override
	public List<NoticeDTO> list() throws Exception {
		List<NoticeDTO> list = null;
		list = notdao.list();
		return list;
	}

	// 글목록 요청페이지
	@Override
	public List<NoticeDTO> list(int startRec, int endRec) throws Exception {
		List<NoticeDTO> list = null;
		list = notdao.list(startRec, endRec);
		return list;
	}
	
	// 글목록 Map
	@Override
	public List<NoticeDTO> listMap() throws Exception {
		List<NoticeDTO> list = null;
		list = notdao.listMap();
		return list;
	}
	

	// 글읽기
	@Override
	public NoticeDTO view(String nnum) throws Exception {
		NoticeDTO notDTO = null;
		notDTO = notdao.view(nnum);
		return notDTO;
	}

	// 글수정
	@Override
	public int modify(NoticeDTO notDTO) throws Exception {
		int cnt = 0;
		cnt = notdao.modify(notDTO);
		return cnt;
	}

	// 글삭제
	@Override
	public int delete(String nnum) throws Exception {
		int cnt = 0;
		cnt = notdao.delete(nnum);
		return cnt;
	}

	// 원글가져오기
	@Override
	public NoticeDTO replyView(String nnum) throws Exception {
		NoticeDTO notDTO = null;
		notDTO = notdao.replyView(nnum);
		return notDTO;
	}

	// 답글쓰기
	@Override
	public int reply(NoticeDTO notDTO) throws Exception {
		int cnt = 0;
		cnt = notdao.reply(notDTO);
		return cnt;
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		int cnt = 0;
		cnt = notdao.totalRec();
		return cnt;
	}

	//검색목록
	@Override
	public List<NoticeDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		List<NoticeDTO> list = null;
		list = notdao.list(startRecord, endRecord, searchType, keyword);
		return list;
	}
	
	
	@Override
	public void list(HttpServletRequest request, Model model) throws Exception {
		logger.info("void notice.list(HttpServletRequest request, Model model) 호출됨!");

		int NUM_PER_PAGE = 10;			 //한페이지에 보여줄 레코드수
		int PAGE_NUM_PER_PAGE = 10;		 //한페이지에 보여줄 페이지수
		
		int reqPage = 0;				 //요청페이지
		int totalRec = 0;				 //전체레코드수
		
		String searchType = null; 		//검색타입
		String keyword  = null;   		//검색어

		PageCriteria pc = null;
		RecordCriteria rc= null;        //검색조건이 없는경우의 레코드 시작, 종료페이지연산
		FindCriteria fc = null;         //검색조건이 있는경우의 레코드 시작, 종료페이지 연산 + 검색타입
		
		List<NoticeDTO> alist = null;
		
		
		// 요청페이지가 없는 경우 1페이지로이동
		if(request.getParameter("reqPage") == null ||
			request.getParameter("reqPage") == "") {
			reqPage = 1;			
		}else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));			
		}		
		
		// 검색어 매개값 체크(searchType, keyword)
		searchType = request.getParameter("searchType");
		keyword = request.getParameter("keyword");
		
		if(keyword == null || keyword.trim().isEmpty()) {
			//검색조건이 없는경우			
			
			rc = new RecordCriteria(reqPage, NUM_PER_PAGE);
			totalRec = notdao.totalRec();
			
			pc = new PageCriteria(rc,totalRec,PAGE_NUM_PER_PAGE);
			
			alist = notdao.list(rc.getStartRecord(), rc.getEndRecord());

			
						
		}else {
			//검색조건이 있는경우
			
			rc = new FindCriteria(reqPage, NUM_PER_PAGE,searchType,keyword);
			
			//검색목록 총레코드수 변환
			totalRec = notdao.SearchTotalRec(
					((FindCriteria)rc).getSearchType(),
					((FindCriteria)rc).getKeyword()
					);
			
			pc = new PageCriteria(rc,totalRec,PAGE_NUM_PER_PAGE);
			
			//검색목록
			alist = notdao.list(
							rc.getStartRecord(), 
							rc.getEndRecord(),
							((FindCriteria)rc).getSearchType(),
							((FindCriteria)rc).getKeyword()
							);
			
		}
		
		model.addAttribute("list", alist);
		model.addAttribute("pc", pc);
		
	}

	//검색총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		int totalRec = 0;
		totalRec = notdao.SearchTotalRec(searchType, keyword);
		return totalRec;
	}



}
