package com.kh.myapp.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.kh.myapp.bbs.dto.RbbsDTO;

@Repository("rbbsDAOImplXML")
public class RbbsDAOImplXML implements RbbsDAO {

	private static final Logger logger = LoggerFactory.getLogger(RbbsDAOImplXML.class);
	
	@Inject
	SqlSession sql;
	
	
	//댓글 등록
	@Override
	public int write(RbbsDTO rbbsDTO) throws Exception {
		return sql.insert("mappers.rbbs.write",rbbsDTO);
	}

	//댓글 목록
	@Override
	public List<RbbsDTO> list(String bnum) throws Exception {
		return sql.selectList("mappers.rbbs.listOld", bnum);
	}

	//댓글 목록
	@Override
	public List<RbbsDTO> list(String bnum, int startRec, int endRec) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("bnum", bnum);
		map.put("startRec", startRec);
		map.put("endRec", endRec);
		return sql.selectList("mappers.rbbs.list", map);
	}

	//댓글 수정
	@Override
	public int modify(RbbsDTO rbbsDTO) throws Exception {
		return sql.update("mappers.rbbs.modify", rbbsDTO);
	}

	//댓글 삭제
	@Override
	public int delete(String rnum) throws Exception {
	int cnt = 0;
	
	//답글존재유무판단
	if(isReply(rnum)) {
	//답글존재
		cnt = sql.update("mappers.rbbs.update_isdel", rnum);
	}else {
	//답글미존재
		cnt = sql.delete("mappers.rbbs.delete", rnum);
	}
		return cnt;
	}

	//답글미존재판단
	private boolean isReply(String rnum) {
		boolean isYN = false;
		int cnt = sql.selectOne("mappers.rbbs.isReply", rnum);
		if(cnt > 0) {
			isYN = true;
		}			
		return isYN;
	}

	//댓글 호감, 비호감
	@Override
	public int goodOrBad(String rnum, String goodOrBad) throws Exception {
		Map<String,Object> map = new HashMap<>();
		map.put("rnum",rnum);
		map.put("goodOrBad", goodOrBad);
		return sql.update("mappers.rbbs.goodOrBad", map);
	}

	//대댓글 등록
	@Override
	public int reply(RbbsDTO rbbsDTO) throws Exception {
		int cnt1=0, cnt2=0;
		
		//댓글대상 읽어오기
		RbbsDTO originDTO = replyView(rbbsDTO.getRrnum());
		logger.info("originDTO:"+originDTO);
		// 이전답글 step 업데이트(원글그룹에 대한 새로정렬 재정의)
		cnt1 = updateStep(originDTO.getRgroup(), originDTO.getRstep());

		Map<String, Object> map = new HashMap<>();
		map.put("originDTO", originDTO);
		map.put("rbbsDTO", rbbsDTO);
		logger.info("map"+map);
		cnt2 = sql.insert("mappers.rbbs.reply", map);
		
		return cnt2;
	}

	
	// 이전답글 step 업데이트 동일그룹의 댓글중에 동일스텝의 글이 있으면 +1갱신
	private int updateStep(int rgroup, int rstep) {
		Map<String, Object> map = new HashMap<>();
		map.put("rgroup", rgroup);
		map.put("rstep", rstep);
		return sql.update("mappers.rbbs.updateStep", map);
		
	}

	//댓글대상 읽어오기
	private RbbsDTO replyView(Integer rrnum) {
		RbbsDTO rdto = null;
		rdto = sql.selectOne("mappers.rbbs.replyView", rrnum);
		return rdto;
	}

	//댓글 통계
	@Override
	public int replyTotalRec(String bnum) throws Exception {
		return sql.selectOne("mappers.rbbs.replyTotalRec", bnum); 
	}

}
