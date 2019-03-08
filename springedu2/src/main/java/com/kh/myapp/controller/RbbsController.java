package com.kh.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kh.myapp.bbs.dto.RbbsDTO;
import com.kh.myapp.bbs.service.RbbsSvc;
import com.kh.myapp.util.PageCriteria;
import com.kh.myapp.util.RecordCriteria;

@RestController
@RequestMapping("/rbbs")
public class RbbsController {

	private static Logger logger = LoggerFactory.getLogger(RbbsController.class);

	@Inject
	RbbsSvc rbbsSvc;

	// 댓글 등록
	@RequestMapping(value = "/posts", method = RequestMethod.POST)
	public ResponseEntity<String> write(@RequestBody RbbsDTO rdto) {
		ResponseEntity<String> resCode = null;
		if (rdto == null) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			return resCode;
		}
		try {
			rbbsSvc.write(rdto);
			resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resCode;
	}

	// 댓글 수정
	@RequestMapping(value = "/posts", method = RequestMethod.PUT)
	public ResponseEntity<String> modify(@RequestBody RbbsDTO rdto) {
		ResponseEntity<String> resCode = null;
		if (rdto == null) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			return resCode;
		}
		try {
			rbbsSvc.modify(rdto);
			resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resCode;
	}

	// 댓글 삭제
	@RequestMapping(value = "/posts/{rnum}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rnum") String rnum) {
		ResponseEntity<String> resCode = null;

		if (rnum == null || rnum.length() == 0) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			return resCode;
		}
		try {
			rbbsSvc.delete(rnum);
			resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resCode;
	}

	// 대댓글 등록
	@RequestMapping(value = "/rposts", method = RequestMethod.POST)
	public ResponseEntity<String> reply(@RequestBody RbbsDTO rdto) {
		ResponseEntity<String> resCode = null;
		if (rdto == null) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			return resCode;
		}
		try {
			rbbsSvc.reply(rdto);
			resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resCode;
	}

	// 호감 비호감
	@RequestMapping(value = "/posts/{goodOrbad}/{rnum}", method = RequestMethod.PUT)
	public ResponseEntity<String> goodOrbad(@PathVariable("goodOrbad") String goodOrbad,
			@PathVariable("rnum") String rnum) {

		ResponseEntity<String> resCode = null;

		if (rnum == null || rnum.length() == 0) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			return resCode;
		}
		try {
			rbbsSvc.goodOrBad(rnum, goodOrbad);
			resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			resCode = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resCode;
	}

	// 댓글 목록 List
	@RequestMapping(value = "/posts/list/{bnum}/{reReqPage}", method = RequestMethod.GET)
	public ResponseEntity<List<RbbsDTO>> list(
			@PathVariable("bnum") String bnum, 
			@PathVariable("reReqPage") Integer reReqPage) {

		ResponseEntity<List<RbbsDTO>> responseEntity = null;
		RecordCriteria rc = new RecordCriteria(reReqPage, 10);

		try {
			responseEntity = new ResponseEntity<List<RbbsDTO>>(
					rbbsSvc.list(bnum, rc.getStartRecord(), rc.getEndRecord()), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<List<RbbsDTO>>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}

		return responseEntity;
	}

	// 댓글 목록 Map
	@RequestMapping(value = "/posts/map/{bnum}/{reReqPage}", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> map(
			@PathVariable("bnum") String bnum,
			@PathVariable("reReqPage") Integer reReqPage) {
		ResponseEntity<Map<String, Object>> responseEntity = null;
		Map<String, Object> map = new HashMap<>();
		RecordCriteria rc = new RecordCriteria(reReqPage, 10);

		try {
			// 페이지 처리
			// =-----------------------------------------------------------------------
			PageCriteria pc = new PageCriteria(rc, rbbsSvc.replyTotalRec(bnum), 10);
			// -------------------------------------------------------------------------------

			map.put("item", rbbsSvc.list(bnum, rc.getStartRecord(), rc.getEndRecord()));
			map.put("pagecriteria", pc);
			responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}

		return responseEntity;
	}
	
	


}
