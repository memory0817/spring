package com.kh.myapp.login;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.kh.myapp.member.dto.MemberDTO;

@Repository("loginDAOImplJDBC")
public class LoginDAOImplJDBC implements LoginDAO {
	
	private static final Logger logger = LoggerFactory.getLogger("LoginDAOImplJDBC.class");
	
	@Inject //같은 타입의 인스턴스를 참조
	private JdbcTemplate jdbcTemplate;
	

/*	@Override
	public boolean login(String id, String pw) {
		
		boolean success = false;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select nickname from member ");
		sql.append("where id=? and pw=? ");
		
		String nickname = jdbcTemplate.queryForObject(sql.toString(), new String[] {id, pw}, String.class);
		if(nickname !=null ) {
			success = true;
		}
		
//		success = jdbcTemplate.queryForObject(sql.toString(), new String[] {id, pw}, Boolean.class);
		
		return success;
	}*/

	
	//회원 유무체크
	@Override
	public boolean isMember(String id , String pw) {
		boolean isMember = false;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(id) from member where id = ? and pw = ? ");

		int cnt = jdbcTemplate.queryForObject(sql.toString(), new String[] {id,pw} , Integer.class);
		if(cnt > 0) {
			isMember = true;
		}
		return isMember;
	}

	
	//로그인
	@Override
	public MemberDTO login(String id, String pw) {
		MemberDTO mdto = new MemberDTO();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select id,pw,tel,nickname,gender,region,birth,cdate,udate from member where id = ? and pw = ? ");
		
		mdto = jdbcTemplate.queryForObject(sql.toString(), new String[] {id,pw} ,
				new BeanPropertyRowMapper<>(MemberDTO.class));
		
		return mdto;
	}

}
