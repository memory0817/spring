package com.kh.myapp.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository(value = "noticeDAOImplXML")
public class NoticeDAOImplXML implements NoticeDAO {

	private static final Logger logger = LoggerFactory.getLogger(NoticeDAOImplXML.class);
	
	@Inject
	SqlSession sql;
	
	// 글쓰기
	@Override
	public int write(NoticeDTO notDTO) throws Exception {		
		return sql.insert("mappers.notice.write", notDTO);
	}

	// 글읽기
	@Override
	public NoticeDTO view(String nnum) throws Exception {
		NoticeDTO notDTO = null;		
		notDTO = sql.selectOne("mappers.notice.view",nnum);
		
		// 조회수 증가
		updateHit(notDTO.getNnum());
		return notDTO;
	}

	//조회수 증가메소드
	private void updateHit(int nnum) {
		sql.update("mappers.notice.updateHit", nnum);		
	}

	// 글수정
	@Override
	public int modify(NoticeDTO notDTO) throws Exception {		
		return sql.update("mappers.notice.modify", notDTO);
	}

	// 글삭제
	@Override
	public int delete(String nnum) throws Exception {
		return sql.delete("mappers.notice.delete", nnum);
	}

	
	// 원글가져오기
	@Override
	public NoticeDTO replyView(String nnum) throws Exception {
		return sql.selectOne("mappers.notice.replyView", nnum);
	}

	// 답글쓰기
	@Override
	public int reply(NoticeDTO notDTO) throws Exception {
		int cnt1=0, cnt2=0;
		//이전 답글 step 업데이트(원글그룹에 대한 세로정렬 재정의)
			cnt1 = updateStep(notDTO.getNgroup(),notDTO.getNstep());
		//답글등록
			cnt2 = sql.insert("mappers.notice.reply", notDTO);
			
		return cnt2;
	}

	//이전답글 step 업데이트
	private int updateStep(int ngroup, int nstep) {
		Map<String, Object> map = new HashMap<>();
		map.put("ngroup", ngroup);
		map.put("nstep", nstep);
		return sql.update("mappers.notice.updateStep", map);
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		return sql.selectOne("mappers.notice.totalRec");
	}
	
	//글목록 전체
	@Override
	public List<NoticeDTO> list() throws Exception {
		return sql.selectList("mappers.notice.listOld");
	}	
	
	// 글목록 요청페이지
	@Override
	public List<NoticeDTO> list(int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sql.selectList("mappers.notice.list", map);
	}

	// 글목록 요청페이지 Map
	@Override
	public List<NoticeDTO> listMap() throws Exception {
		return sql.selectList("mappers.notice.listNew");
	}
	
	//검색목록
	@Override
	public List<NoticeDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("endRecord", endRecord);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		return sql.selectList("mappers.notice.flist", map);
	}

	//검색총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		return sql.selectOne("mappers.notice.searchTotalRec", map);
	}



}
