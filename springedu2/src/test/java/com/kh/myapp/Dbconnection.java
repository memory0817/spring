package com.kh.myapp;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class) //junit5버전 사용
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
class Dbconnection {
	
	
	private static final Logger logger = LoggerFactory.getLogger(Dbconnection.class);
	
	@Inject
	JdbcTemplate jdbcTemplate;
	
//	@Inject
	@Autowired //같은의미
	DataSource dataSource;
//	@Test
	void test() {
		System.out.println("데이타소스:" + dataSource);
		logger.info("데이타소스:"+dataSource);
	}
	
	
	@Test 

	void testJdbcTemplate() {
		logger.info("JdbcTemplate:"+jdbcTemplate);
		try {
			Connection conn = jdbcTemplate.getDataSource().getConnection();
			logger.info("db연결 성공!!:"+conn);
		} catch (SQLException e) {
			fail("db연결 실패!!");
			e.printStackTrace();
		}
	}

}
