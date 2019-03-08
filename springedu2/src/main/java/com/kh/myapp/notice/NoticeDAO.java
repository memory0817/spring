package com.kh.myapp.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;



public interface NoticeDAO {
	
	
	//글쓰기
	int write(NoticeDTO notDTO) throws Exception;	
	
	
	//글목록
	List<NoticeDTO> list() throws Exception;
	List<NoticeDTO> list(int startRec, int endRec) throws Exception;
	List<NoticeDTO> listMap() throws Exception;
	
	
	//글읽기
	NoticeDTO view(String nnum) throws Exception;
	
	
	// 글수정
	int modify(NoticeDTO notDTO) throws Exception;
	
	
	// 글삭제
	int delete(String nnum) throws Exception;

	
	// 원글가져오기
	NoticeDTO replyView(String nnum) throws Exception;
	
	
	// 답글쓰기
	int reply(NoticeDTO notDTO) throws Exception;
	
	// 게시글 총계
	int totalRec() throws Exception;

	// 검색목록
	List<NoticeDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception;
	
	// 검색 총계
	int SearchTotalRec(String searchType, String keyword) throws Exception;
	

}
