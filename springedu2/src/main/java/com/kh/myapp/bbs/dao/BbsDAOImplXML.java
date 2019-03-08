package com.kh.myapp.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.kh.myapp.bbs.dto.BbsDTO;

@Repository(value = "bbsDAOImplXML")
public class BbsDAOImplXML implements BbsDAO {

	private static final Logger logger = LoggerFactory.getLogger(BbsDAOImplXML.class);

	@Inject
	SqlSession sqlSession;

	// 글쓰기
	@Override
	public int write(BbsDTO bbsDTO) throws Exception {
		return sqlSession.insert("mappers.bbs.write", bbsDTO);
	}

	
	// 글읽기
	@Override
	public BbsDTO view(String bnum) throws Exception {
		BbsDTO bbsDTO = null;
		
		bbsDTO = sqlSession.selectOne("mappers.bbs.view", bnum);

		// 조회수 증가
		updateHit(bbsDTO.getBnum());
		return bbsDTO;
	}
	//조회수 증가메소드
	private void updateHit(int bnum) {
		sqlSession.update("mappers.bbs.updateHit", bnum);
	}

	// 글수정
	@Override
	public int modify(BbsDTO boardDTO) throws Exception {
		return sqlSession.update("mappers.bbs.modify", boardDTO);
	}

	// 글삭제
	@Override
	public int delete(String bnum) throws Exception {
		int cnt = 0;
		// 답글존재유무 판단
		if (isReply(bnum)) {
			// 답글존재
			cnt = sqlSession.update("mappers.bbs.update_isdel", bnum);
		} else {
			// 답글미존재
			cnt = sqlSession.delete("mappers.bbs.delete", bnum);
		}
		return cnt;

	}
	// 답글존재유무 판단 메소드
	private boolean isReply(String bnum) {
		int cnt = 0;
		boolean isYN = false;

		cnt = sqlSession.selectOne("mappers.bbs.isReply", bnum);
		if (cnt > 0) {
			isYN = true;
		}

		return false;
	}

	// 원글가져오기
	@Override
	public BbsDTO replyView(String bnum) throws Exception {
		return sqlSession.selectOne("mappers.bbs.replyView", bnum);
	}

	// 답글쓰기
	@Override
	public int reply(BbsDTO bbsDTO) throws Exception {
		int cnt1=0, cnt2=0;
		
		//이전 답글 step 업데이트(원글그룹에 대한 세로정렬 재정의)
			cnt1 = updateStep(bbsDTO.getBgroup(), bbsDTO.getBstep());
		//답글등록		
			cnt2=sqlSession.insert("mappers.bbs.reply", bbsDTO);
			
			return cnt2;
	}
	
	//이전답글 step 업데이트
	private int updateStep(int bgroup, int bstep) {
		Map<String, Object> map= new HashMap<>();
		map.put("bgroup", bgroup);
		map.put("bstep", bstep);
		return sqlSession.update("mappers.bbs.updateStep", map);
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		return sqlSession.selectOne("mappers.bbs.totalRec");
	}
	
	//글목록 전체
	public List<BbsDTO> list() throws Exception{
		return sqlSession.selectList("mappers.bbs.listOld");
	}
	
	// 글목록 요청페이지
	@Override
	public List<BbsDTO> list(int starRec, int endRec) throws Exception {
		Map<String, Object> map= new HashMap<>();
		map.put("starRec", starRec);
		map.put("endRec", endRec);
		return sqlSession.selectList("mappers.bbs.list",map);
	}

	//검색목록
	@Override
	public List<BbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("endRecord", endRecord);
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		return sqlSession.selectList("mappers.bbs.flist", map);
	}
	
	//검색총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("searchType", searchType);
		map.put("keyword", keyword);
		
		return sqlSession.selectOne("mappers.bbs.searchTotalRec",map);
	}



}
