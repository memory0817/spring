package com.kh.myapp.bbs.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.kh.myapp.bbs.dto.BbsDTO;

//@Repository
public class BbsDAOImpl implements BbsDAO {

	
	private static Logger logger = LoggerFactory.getLogger(BbsDAOImpl.class);
	
	@Inject
	JdbcTemplate JdbcTemplate;
	
	
	
	//글쓰기
	@Override
	public int write(BbsDTO bbsDTO) throws Exception {
		
		logger.info("int write(BbsDTO bbsDTO) 호출됨:" + bbsDTO);
		
		int cnt = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO board (bnum, btitle, bid, bnickname, ");
		sql.append("bhit, bcontent, bgroup, bstep, bindent) ");
		sql.append("VALUES(BOARDNUM_SEQ.nextval,?,?,?,0,?,boardnum_seq.currval,0,0) ");

		
		cnt = JdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bbsDTO.getBtitle());
				ps.setString(2, bbsDTO.getBid());
				ps.setString(3, bbsDTO.getBnickname());
				ps.setString(4, bbsDTO.getBcontent());
				
			}
		});
		
		if(cnt>0) {
			logger.info("등록건수 : " + cnt);
		}else {
			logger.info("등록건수 : " + cnt);
		}
		
		return cnt;
	}
	
	//글목록
	@Override
	public List<BbsDTO> list() throws Exception {
		
		logger.info("List<BbsDTO> list() 호출됨!");
		
		List<BbsDTO> list = null;
		
		StringBuffer sql = new StringBuffer();

		sql.append("select bnum, btitle, bid, bnickname, bcdate, budate, bhit, bcontent, bgroup, bstep, bindent ");
		sql.append("from (select * from board order by bgroup desc, bstep asc) ");
		sql.append("where rownum >=1 and rownum < 25 ");
		
		list = (ArrayList<BbsDTO>)JdbcTemplate.query(
				sql.toString(), 
				new BeanPropertyRowMapper(BbsDTO.class));
		
		return list;
	}

	//글목록
	@Override
	public List<BbsDTO> list(int startRec, int endRec) throws Exception {
		
		logger.info("List<BbsDTO> list(int startRec, int endRec) 호출됨");
		
		List<BbsDTO> list = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select t2.* ");
		sql.append("from (select row_number() over (order by bgroup desc, bstep asc) as num, t1.* ");
		sql.append("      from board t1) t2 ");
		sql.append("where num between ? and ? ");

		
		
		list = (ArrayList<BbsDTO>)JdbcTemplate.query(
				sql.toString(), 
				new Object[] {startRec, endRec},
				new BeanPropertyRowMapper<BbsDTO>(BbsDTO.class));	
		
		return list;
	}
	
	//글읽기
	@Override
	public BbsDTO view(String bnum) throws Exception {
		logger.info("BbsDTO view(String bnum) 호출됨!");
		
		BbsDTO bbsdto = null;
		
		StringBuffer sql = new StringBuffer();

		sql.append("select bnum, btitle, bid, bnickname, bcdate, budate, bhit, bcontent, bgroup, bstep, bindent ");
		sql.append("from board where bnum=? ");

		
		bbsdto = JdbcTemplate.queryForObject(
				sql.toString(), 
				new Object[] {bnum},
				new BeanPropertyRowMapper<BbsDTO>(BbsDTO.class));
		
		//조회수 증가
		updateHit(bbsdto.getBnum());
		
		return bbsdto;
	}
	
	//조회수 증가
	private void updateHit(int bnum) {
		logger.info("void updateHit(int bnum) 호출됨!");
		
		int cnt = 0;
		
		StringBuffer sql = new StringBuffer();

		sql.append("update board set bhit = bhit+1 ");
		sql.append("where bnum=? ");
		
		//람다식
		cnt = JdbcTemplate.update(sql.toString(), ps-> {ps.setInt(1, bnum);});
			
		//익명클래스
/*		cnt = JdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, bnum);
							}
		});*/
		if(cnt>0) {
			logger.info("조회건수증가 : " + cnt);
		}else {
			logger.info("조회건수증가 : " + cnt);
		}
		
	}

	// 글수정
	@Override
	public int modify(BbsDTO bbsDTO) throws Exception {
		logger.info("int modify(BbsDTO bbsDTO) 호출됨!");
		
		int cnt = 0;
		
		StringBuffer sql = new StringBuffer();

		sql.append("update board set btitle=?, budate=sysdate, bcontent=? ");
		sql.append("where bnum=? ");
		
		
		cnt = JdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bbsDTO.getBtitle());
				ps.setString(2, bbsDTO.getBcontent());
				ps.setInt(3, bbsDTO.getBnum());			
			}			
		});
		
		if(cnt>0) {
			logger.info("수정건수 : " + cnt);
		}else {
			logger.info("수정건수 : " + cnt);
		}
		
		
		return cnt;
	}

	// 글삭제
	@Override
	public int delete(String bnum) throws Exception {
		logger.info("int delete(String bnum) 호출됨!");
		
		int cnt = 0;
		StringBuffer sql = new StringBuffer();
		
		//답글존재유무 판단
		if(isReply(bnum)) {
			//답글존재
			sql.append("update board set isdel = 'Y' where bnum = ? ");
			JdbcTemplate.update(sql.toString(), bnum);		
			
		}else {		
			//답글 미존재
			
			/*sql.append("delete from board where bnum=? " + bnum);		
			  JdbcTemplate.update(sql.toString());  이런방법도 있고 */					
			 sql.append("delete from board where bnum=? ");							
			 cnt = JdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {						
				 @Override
				 public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, Integer.valueOf(bnum));				
				}
			});  					
		}
		
		
		if(cnt>0) {
			logger.info("삭제건수 : " + cnt);
		}else {
			logger.info("삭제건수 : " + cnt);
		}		
		
		return cnt;
	}

	
	//원글에 대한 답글이 존재하는지 판단
	private boolean isReply(String bnum) {
		boolean isYN = false;
		int cnt = 0;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(bnum) from board ");
		sql.append("where bgroup in ( select bgroup from board t1 ");
		sql.append("                  where t1.bnum = ?) ");
		sql.append("and bnum <> ?" );
		
		cnt = JdbcTemplate.queryForObject(//sql문, 파라미터, 리턴타입
				sql.toString(), new Object[] {bnum, bnum}, Integer.class);
		
		if(cnt > 0) {
			isYN = true;
		}
		
		return isYN;
	}

	// 원글가져오기
	@Override
	public BbsDTO replyView(String bnum) throws Exception {
		logger.info("BbsDTO replyView(String bnum) 호출됨!");
		
		BbsDTO bbsdto = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select bnum,btitle,bid,bnickname,bcdate,budate,");
		sql.append("			 bhit,bcontent,bgroup,bstep,bindent ");
		sql.append(" from  board where bnum=? ");
		
		bbsdto = JdbcTemplate.queryForObject(
				sql.toString(), 
				new Object[] {bnum},
				new BeanPropertyRowMapper<BbsDTO>(BbsDTO.class));
		
		return bbsdto;
	}

	// 답글쓰기
	@Override
	public int reply(BbsDTO bbsDTO) throws Exception {
		logger.info("void reply(BbsDTO bbsDTO) 호출됨!");
		
		int cnt1 = 0, cnt2 = 0;

		// 이전답글 step 업데이트
		cnt1 = updateStep(bbsDTO.getBgroup(), bbsDTO.getBstep());

		StringBuffer sql = new StringBuffer();
		sql.append("insert into board(bnum,btitle,bid,bnickname,bhit,bcontent,bgroup,bstep,bindent) ");
		sql.append("values(boardnum_seq.nextval,?,?,?,0,?,?,?,?) ");

		cnt2 = JdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, bbsDTO.getBtitle());
				ps.setString(2, bbsDTO.getBid());
				ps.setString(3, bbsDTO.getBnickname());
				ps.setString(4, bbsDTO.getBcontent());
				ps.setInt(5, bbsDTO.getBgroup()); // 원글번호 = 원글그룹
				ps.setInt(6, bbsDTO.getBstep() + 1); // 원글그룹의 세로정렬(답글단계)
				ps.setInt(7, bbsDTO.getBindent() + 1); // 원글그릅의 가로정렬(들여쓰기)
				
			}
		}); 
		
		if(cnt2>0) {
			logger.info("답글게제건수 : " + cnt2);
		}else {
			logger.info("답글게제건수 : " + cnt2);
		}
		
		return cnt1;
		
	}

	private int updateStep(int bgroup, int bstep) {
		logger.info("int updateStep(int bgroup, int bstep) 호출됨!");
		
		int cnt = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("update board set bstep= bstep+1 where bgroup=? and bstep>? ");
		
		cnt = JdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setInt(1, bgroup);
				ps.setInt(2, bstep);
				
			}
		});
		
		if(cnt>0) {
			logger.info("게시글그룹/게시글스텝  1증가 : " + cnt);
		}else {
			logger.info("게시글그룹/게시글스텝  1증가 : " + cnt);
		}
		
		
		return cnt;
	}

	// 게시글 총계
	@Override
	public int totalRec() throws Exception {
		logger.info("int totalRec() 호출됨!");
		
		int totalRec = 0;
		
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) totalRec from board ");

		totalRec =(Integer)JdbcTemplate.queryForObject(sql.toString(), Integer.class);	
		
		
		return totalRec;
	}
	
	// 검색목록
	@Override
	public List<BbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		logger.info("ArrayList<BbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) 호출됨!!");
		
		List<BbsDTO> list = new ArrayList<>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t2.* ");
		sql.append("from (select row_number() over (order by bgroup desc, bstep asc) as num, t1.* ");
		sql.append("      from board t1 ");
		sql.append("      where bnum > 0 ");
		

		switch(searchType) {
		case "TC": // 제목 + 내용
			sql.append("and btitle like '%' || ? ||'%' or bcontent like '%'|| ? ||'%' ");
			break;
		case "T": // 제목
			sql.append("and btitle like '%' || ? ||'%' ");
			break;

		case "C": // 내용
			sql.append("bcontent like '%'|| ? ||'%' ");
			break;

		case "N": // 작성자
			sql.append("bnickname like '%'|| ? ||'%' ");
			break;

		case "I": // 아이디
			sql.append("bid like '%'|| ? ||'%' ");
			break;
			
		default: // 제목 + 내용 + 작성자
			sql.append("and btitle like '%' || ? ||'%' or bcontent like '%'|| ? ||'%' or bnickname like '%'|| ? ||'%' ");			
			break;
		}
		
		sql.append("      ) t2 ");
		sql.append("where num between ? and ? ");
		
		Object[] obj = null; //jdbcTemplate.query매소드의 2번째인자  변수
		switch(searchType) {
		case "TC":
			obj = new Object[] {keyword,keyword,startRecord,endRecord};
			break;
		case "T":				
		case "C":				
		case "N":				
		case "I":
			obj = new Object[] {keyword,startRecord,endRecord};
			break;
		default:
			obj = new Object[] {keyword,keyword,keyword,startRecord,endRecord};
			break;
		}	
		
		list = (ArrayList<BbsDTO>)JdbcTemplate.query(
				sql.toString(), 
				obj,
				new BeanPropertyRowMapper<BbsDTO>(BbsDTO.class));
		
		return list;
	}

	// 검색 총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		logger.info("int SearchTotalRec(String searchType, String keyword) 호출됨!");
		int totalRec = 0;
		

		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) totalRec ");
		sql.append("from (select row_number() over (order by bgroup desc, bstep asc) as num, t1.* ");
		sql.append("      from board t1 ");
		sql.append("      where bnum > 0 ");

		switch(searchType) {
		case "TC": // 제목 + 내용
			sql.append("and btitle like '%' || ? ||'%' or bcontent like '%'|| ? ||'%' ");
			break;
		case "T": // 제목
			sql.append("and btitle like '%' || ? ||'%' ");
			break;

		case "C": // 내용
			sql.append("bcontent like '%'|| ? ||'%' ");
			break;

		case "N": // 작성자
			sql.append("bnickname like '%'|| ? ||'%' ");
			break;

		case "I": // 아이디
			sql.append("bid like '%'|| ? ||'%' ");
			break;
			
		default: // 제목 + 내용 + 작성자
			sql.append("and btitle like '%' || ? ||'%' or bcontent like '%'|| ? ||'%' or bnickname like '%'|| ? ||'%' ");			
			break;
		}
		sql.append("      ) t2 ");
		
		
		Object[] obj = null;
		
		switch(searchType) {
		case "TC":
			obj = new Object[] {keyword,keyword};
			break;
		case "T":				
		case "C":				
		case "N":				
		case "I":
			obj = new Object[] {keyword};
			break;
		default:
			obj = new Object[] {keyword,keyword,keyword};
			break;
		}
				
		
		totalRec = (Integer)JdbcTemplate.queryForObject(sql.toString(), obj ,Integer.class);
		
		return totalRec;
	}

}
