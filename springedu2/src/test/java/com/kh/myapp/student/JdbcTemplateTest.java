package com.kh.myapp.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.Data;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class JdbcTemplateTest {

	private static Logger Logger = LoggerFactory.getLogger(JdbcTemplateTest.class);

	@Inject
	JdbcTemplate jt;

	StringBuffer sql;
	int cnt;

	@BeforeEach
	void before() {
		sql = new StringBuffer();
	}

	@Test @Disabled
	void insert() {
		sql.append("insert into student (id,name,kor,eng,math) ");
		sql.append(" values (?,?,?,?,?) ");

		cnt = jt.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				
					PreparedStatement pstmt = con.prepareStatement(sql.toString());

					pstmt.setString(1, "id2");
					pstmt.setString(2, "닉네임2");
					pstmt.setInt(3, 70);
					pstmt.setInt(4, 80);
					pstmt.setInt(5, 90);
					
					
					return pstmt;			
			}
		});

		Logger.info("생성건수 : " + cnt);
		
	}

	
	@Test @Disabled
	void insert2() {
		sql.append("insert into student (id,name,kor,eng,math) ");
		sql.append(" values ('id1','이름1',51,77,99) ");
		
		cnt = jt.update(sql.toString());
		Logger.info("생성건수 : " + cnt);
	}
	
	
	//insert1 람다식으로 바꿈
	@Test @Disabled
	void insert3() {
		sql.append("insert into student (id,name,kor,eng,math) ");
		sql.append(" values (?,?,?,?,?) ");

		cnt = jt.update(con -> {							
					PreparedStatement pstmt = con.prepareStatement(sql.toString());

					pstmt.setString(1, "id4");
					pstmt.setString(2, "닉네임4");
					pstmt.setInt(3, 70);
					pstmt.setInt(4, 80);
					pstmt.setInt(5, 90);						
					return pstmt;			
			}
		);

		Logger.info("생성건수 : " + cnt);
		
	}
	
	
	
	@Test @Disabled
	void insert4() {
		
		sql.append("insert into student (id,name,kor,eng,math) ");
		sql.append(" values (?,?,?,?,?) ");
		
		cnt = jt.update(sql.toString(),"id5","닉네임5",95,92,94);
		Logger.info("생성건수 : " + cnt);
		
	}


	@Test @Disabled
	void insert5() {
		sql.append("insert into student (id,name,kor,eng,math) ");
		sql.append(" values (?,?,?,?,?) ");
		
		cnt = jt.update(sql.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, "id6");
				ps.setString(2, "닉네임6");
				ps.setInt(3, 85);
				ps.setInt(4, 65);
				ps.setInt(5, 95);
				
			}
		});
		
		Logger.info("생성건수 : " + cnt);
	}

	@Test @Disabled //insert5 람다식으로 바꾸기
	void insert6() {
		sql.append("insert into student (id,name,kor,eng,math) ");
		sql.append(" values (?,?,?,?,?) ");
		
		cnt = jt.update(sql.toString(), ps -> {		
			
				ps.setString(1, "id9");
				ps.setString(2, "닉네임9");
				ps.setInt(3, 75);
				ps.setInt(4, 67);
				ps.setInt(5, 97);
		
			});
		
		Logger.info("생성건수 : " + cnt);
		}
	
	
	@Test @Disabled
	void insert7() {
		sql.append("insert into student (id,name,kor,eng,math) ");
		sql.append(" values (?,?,?,?,?) ");
		
		
		int [] ty = {Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER};
		cnt = jt.update(
				sql.toString(), 
				new Object[] {"id8","닉네임8",100,100,100},
				ty
				);
		
		Logger.info("생성건수 : " + cnt);
		
	}


	@Test @Disabled
	void insert8() {
		sql.append("insert into student (id,name,kor,eng,math) ");
		sql.append(" values (?,?,?,?,?) ");
		
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		
		jt.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						sql.toString(),new String[] {"id"});

				pstmt.setString(1, "id16");
				pstmt.setString(2, "닉네임16");
				pstmt.setInt(3, 50);
				pstmt.setInt(4, 84);
				pstmt.setInt(5, 93);
				
				return pstmt;
				
			}
		}, generatedKeyHolder);	
		
		Logger.info("키값:" + generatedKeyHolder.toString());
		
	}

	
	
	//조회부분
	@Test @Disabled
	void list() {
		sql.append("select id,name,kor,eng,math from student "); //where kor > ?
		List<Map<String,Object>> list = null;
		
		// 하나의 레코드를 컬럼은 key, 값은 value로 매핑하여 맵객체를 만들고 List에 담아올때사용
		list = jt.queryForList(sql.toString());
		
		
		for(Map<String,Object> map : list) {
			Logger.info(map.toString());
		}
	}
	
	
	
	@Test @Disabled
	void list2() {
		
		sql.append("select eng from student ");
		
		@Data
		class Student {
			private String id;
			private String name;
			private int kor, eng, math;
		}
		
		List<Integer> list = null;
		
		//1개 레코드에 1개 컬럼값만을 기본타입으로 List에 담아올 때 사용
		list = jt.queryForList(sql.toString(), Integer.class);
		
		for(Integer s : list) {
			Logger.info(s.toString());
		}
		
	}
	
	
	
	
	@Test @Disabled
	void list3() {
		sql.append("select id,name,kor,eng,math from student where kor > ? "); //and math > ?
		
		List<Map<String,Object>> list = null;
		
		// 하나의 레코드를 컬럼은 key, 값은 value로 매핑하여 맵객체를 만들고 List에 담아올때사용
		list = jt.queryForList(sql.toString(), 80); //,80
		
		
		list.stream().forEach(map -> {
			Logger.info(map.toString());
		});
	}
	
	
	@Test @Disabled
	void list4() {
		sql.append("select name from student where kor > ? and math > ? "); 
		
		List<String> list = null;
		
		// 하나의 레코드를 컬럼은 key, 값은 value로 매핑하여 맵객체를 만들고 List에 담아올때사용
		list = jt.queryForList(sql.toString(), String.class, 50, 50); 
		
		for(String s : list) {
			Logger.info(s.toString());
		}
		
	}
	
	
	@Test @Disabled
	void list5() {
		sql.append("select name from student where kor > ? and math > ? "); 
		
		List<String> list = null;
		
		// 하나의 레코드를 컬럼은 key, 값은 value로 매핑하여 맵객체를 만들고 List에 담아올때사용
		list = jt.queryForList(sql.toString(), new Object[] {80,90}, String.class);
		
		for(String s : list) {
			Logger.info(s.toString());
		}
		
		
	
	} 
	
	
	@Test @Disabled
	void list6() {
		sql.append("select id,name,kor,eng,math from student where kor > ? and math > ? "); 
		
		List<Map<String,Object>> list = null;
		
		// 2번째매개값 치환변수에 대해 3번째매개값으로 타입을 변환해주고자할때 사용
		list = jt.queryForList(
				sql.toString(),
				new Object[] {"80","90"}, 
				new int[] {Types.INTEGER, Types.INTEGER});
		
		for(Map<String,Object> map : list) {
			Logger.info(map.toString());
		}
	}
	
	
	
	@Test @Disabled
	void list7() {
		sql.append("select name from student where kor > ? and math > ? ");
		
		List<String> list = null;
		
		list = jt.queryForList(
				sql.toString(), 
				new Object[] {"50","80"}, 
				new int[] {Types.INTEGER, Types.INTEGER}, 
				String.class);
		
		list.stream().forEach(s ->Logger.info(s.toString()));
		
		
	}
	
	

	@Test @Disabled
	void list8() {
		sql.append("select * from student where name='이름1'");		
		Map<String,Object> map = null;		
		//레코드 하나일때 사용가능이고 맵형식으로 맵타입으로 리턴해서 읽어온다
		map = jt.queryForMap(sql.toString());		
		//Logger.info(map.toString());
		//키값으로 컬럼이름을 가져온다. 벨류가 컬럼값이된다.
		Logger.info("list8 : "+map.keySet().toString());
		Logger.info("list8 : 아이디- " + map.get("ID"));
	}
	
	//나머지 map들도 이하동문같다.
	
	
	
	@Test @Disabled
	void list9() {
		
		sql.append("select name from student where name='이름1'");	
		String name = null;
		//단일행에 단일컬럼 조회시 사용
		name = (String)jt.queryForObject(sql.toString(), String.class);
		
		Logger.info(name);
		
	}
	
	
	
	@Test @Disabled
	void list9_2() {
		
		sql.append("select count(*) from student where kor >=50");	
		int cnt = 0;
		//단일행에 단일컬럼 조회시 사용
		cnt = jt.queryForObject(sql.toString(), Integer.class);
		
		Logger.info("국어점수가 50이상인 학생수: " + cnt);
		
	}
	

	
	
	@Test @Disabled
	void list11() {
		
		sql.append("select count(*) from student where kor >= ? and math >= ? ");	
		
		
		int cnt = 0;
		
		// 끝에 치환될 값 넣어주면 된다.
		cnt = jt.queryForObject(sql.toString(), Integer.class, 50, 95);
		
		Logger.info("국어점수 50이상, 수학점수 95이상인 학생수: " + cnt);
		
	}
	
	
	
	@Test @Disabled
	void list12() {
		sql.append("select count(*) from student where kor >= ? and math >= ? ");			
		int cnt = 0;
		// 치환될값 순서가 중간이라서 배열형태로 준다
		cnt = jt.queryForObject(sql.toString(), new Object[] {50, 95} , Integer.class);
		Logger.info("국어점수 50이상, 수학점수 95이상인 학생수: " + cnt);
	}
	
	
	
	//RowMapper
	
	@Test @Disabled
	void list10() {
		
		sql.append("select * from student where name='이름1'");	
		
		
		Student s = null;
		
		//테이블 레코드와 객체를 매핑해줄때 사용 (전제조건 : 테이블 컬럼명과 객체의 setter매소드명이 같아야한다.
		s = (Student)jt.queryForObject(
				sql.toString(), 
				new BeanPropertyRowMapper<>(Student.class));
		
		Logger.info(s.toString());
		
	}
	

	
	@Test @Disabled
	void list13() {
		
		sql.append("select id,name,kor,eng,math from student where name = '이름1'");	
		
		
		Student2 s = null;
		
		//테이블 레코드와 객체를 매핑해줄때 사용 (전제조건 : 테이블 컬럼명과 객체의 setter매소드명이 같아야한다. 
		//지금 이 예제는 수동으로 값을 매핑해준 결과이다. 어차피 한행을 가져오니까 if(next) 필요x 
		s = jt.queryForObject(
				sql.toString(), 
				new RowMapper<Student2>() {

					@Override
					public Student2 mapRow(ResultSet rs, int rowNum) throws SQLException {
						Student2 s = new Student2();
						Logger.info("result=" + rs);
												s.setSid(rs.getString("id"));
						s.setSname(rs.getString("name"));
						s.setSkor(rs.getInt("kor"));
						s.setSeng(rs.getInt("eng"));
						s.setSmath(rs.getInt("math"));
						return s;
					}					
				}				
			);
		
		Logger.info(s.toString());
		
	}
	
	
	

	@Test @Disabled
	void list14() {
		
		sql.append("select id,name,kor,eng,math from student where kor >= ?");
		
		Student s = null;
		s = jt.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, 50); //매개값이있다고할경우 이런식으로
				return pstmt;
			}
		}, new ResultSetExtractor<Student>() {

			@Override
			public Student extractData(ResultSet rs) throws SQLException, DataAccessException {
				Student student = new Student();
				while(rs.next()){
					student.setId(rs.getString("id"));
					student.setName(rs.getString("name"));
					student.setKor(rs.getInt("kor"));
					student.setEng(rs.getInt("eng"));
					student.setMath(rs.getInt("math"));
				}
				return student;
			}
			
		});
		Logger.info(s.toString());
		
	}
	
	
	@Test @Disabled //list14람다식
	void list15() {
		
		sql.append("select id,name,kor,eng,math from student where kor >= ?");
		
		Student s = null;
		s = jt.query(con -> {			
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, 50); //매개값이있다고할경우 이런식으로
				return pstmt;			
		}, rs-> {			
				Student student = new Student();
				while(rs.next()){
					student.setId(rs.getString("id"));
					student.setName(rs.getString("name"));
					student.setKor(rs.getInt("kor"));
					student.setEng(rs.getInt("eng"));
					student.setMath(rs.getInt("math"));
				}
				return student;
			
		
		});
		Logger.info(s.toString());
		
	}
	
	
	
	@Test @Disabled//자동매핑
	void list16() {
		sql.append("select id,name,kor,eng,math from student where kor >= ?");
		
		List<Student> list = null;
		
		list = jt.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, 70);
			  return pstmt;
			}
		}, new BeanPropertyRowMapper<Student>(Student.class));
		
//		list.stream().forEach(s->Logger.info(s.toString()));
		for(Student s : list) {
			Logger.info(s.toString());
		}
		
	}
	
	
	
	@Test @Disabled //수동매핑
	void list17() {
		sql.append("select id,name,kor,eng,math from student where kor >= ?");
		
		List<Student> list = null;
		
		list = jt.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, 70);
			  return pstmt;
			}
		}, new RowMapper<Student>() { //Row Mapper 직접구현

			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				 Student s = new Student();
				 s.setId(rs.getString("id"));
				 s.setName(rs.getString("name"));
				 s.setKor(rs.getInt("kor"));
				 s.setEng(rs.getInt("eng"));
				 s.setMath(rs.getInt("math"));				 
				 
				return s;
			}
		});
		
		list.stream().forEach(s-> Logger.info(s.toString()));
		
	}
	
	
	
	@Test @Disabled
	void list18() {
		sql.append("select id,name,kor,eng,math from student where kor >= ? and math >= ? ");
		
		List<Student> result = null;
		
		result = jt.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql.toString());				
				return pstmt;
			}
		}, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, 70);
				ps.setInt(2, 90);				
			}
		}, new ResultSetExtractor<List<Student>>() {

			@Override
			public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Student> list = new ArrayList<Student>();
				Student s = new Student();
				while(rs.next()) {
				 s.setId(rs.getString("id"));
				 s.setName(rs.getString("name"));
				 s.setKor(rs.getInt("kor"));
				 s.setEng(rs.getInt("eng"));
				 s.setMath(rs.getInt("math"));	
				 list.add(s);
				}
				return list;
			}
		});
		
		for(Student s : result) {
			Logger.info(s.toString());
		}
		
//		result.stream().forEach(s-> Logger.info(s.toString()));
	
	}
	
	
	
	
	@Test @Disabled //test18 람다식
	void list19() {
		sql.append("select id,name,kor,eng,math from student where kor >= ? and math >= ? ");
		
		List<Student> result = null;
		
		result = jt.query( con -> {			
				PreparedStatement pstmt = con.prepareStatement(sql.toString());				
				return pstmt;
		}, ps-> {			
				ps.setInt(1, 70);
				ps.setInt(2, 90);				
			
		}, rs -> {			
				List<Student> list = new ArrayList<Student>();
				Student s = new Student();
				while(rs.next()) {
				 s.setId(rs.getString("id"));
				 s.setName(rs.getString("name"));
				 s.setKor(rs.getInt("kor"));
				 s.setEng(rs.getInt("eng"));
				 s.setMath(rs.getInt("math"));	
				 list.add(s);
				}
				return list;			
		});
		
		result.stream().forEach(student-> Logger.info(student.toString()));
	
	}
	
	
	
	
	@Test @Disabled
	void update() {
		sql.append("update student set kor=?, eng=?, math=? where name=?");
		int cnt = 0;
		cnt = jt.update(
				  sql.toString(), 
				  new Object[] {"100","100","100","이름1"}, 
				  new int[] {Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.VARCHAR});
		
		Logger.info("갱신 갯수: " + cnt);
	}
	
	
	
	@Test //@Disabled
	void update2() {
		sql.append("update student set kor=?, eng=?, math=? where name='이름1'");
		int cnt = 0;
		jt.update(sql.toString(), 
				  new Object[] {
				      Integer.parseInt("90"),
				      Integer.parseInt("90"),
				      Integer.parseInt("90")}
				  );
		
		Logger.info("갱신 갯수: " + cnt);
	}
	
	
	
	
	
}
	 
	


//익명클래스가 만들어짐.
@Data
class Student {
	private String id, name;
	private int kor, eng, math;
}

@Data
class Student2 {
	private String sid, sname;
	private int skor, seng, smath;			
}

