package com.kh.myapp.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kh.myapp.bbs.dto.BbsDTO;
import com.kh.myapp.bbs.service.BbsSvc;
import com.kh.myapp.notice.NoticeDTO;
import com.kh.myapp.notice.NoticeSvc;
import com.kh.myapp.util.RecordCriteria;

@RestController
@RequestMapping("/main")
public class MainController {
	
	private static Logger logger = LoggerFactory.getLogger(MainController.class);

	
	@Inject
	NoticeSvc noSvc;
	
	@Inject
	BbsSvc bSvc;
	
	//메인 공지사항출력
	@RequestMapping(value = "/posts/list/{nnum}/{reReqPage}", method = RequestMethod.GET)
	public ResponseEntity<List<NoticeDTO>> list(@PathVariable("nnum") String nnum, 
			@PathVariable("reReqPage") Integer reReqPage){
		
		ResponseEntity<List<NoticeDTO>> responseEntity = null;
		RecordCriteria rc = new RecordCriteria(reReqPage, 10);
		
		try {
			responseEntity = new ResponseEntity<List<NoticeDTO>>(
					noSvc.list(rc.getStartRecord(), rc.getEndRecord()), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<List<NoticeDTO>>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}	
		
		
		return responseEntity;
	}
	
	//메인 공지사항출력
	@RequestMapping(value = "/posts/list2/{bnum}/{reReqPage}", method = RequestMethod.GET)
	public ResponseEntity<List<BbsDTO>> list2(@PathVariable("bnum") String bnum, 
			@PathVariable("reReqPage") Integer reReqPage){
		
		ResponseEntity<List<BbsDTO>> responseEntity = null;
		RecordCriteria rc = new RecordCriteria(reReqPage, 10);
		
		try {
			responseEntity = new ResponseEntity<List<BbsDTO>>(
					bSvc.list(rc.getStartRecord(), rc.getEndRecord()), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<List<BbsDTO>>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}	
		
		
		return responseEntity;
	}
	
}
