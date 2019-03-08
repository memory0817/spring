package com.kh.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.myapp.member.dto.MemberDTO;
import com.kh.myapp.notice.NoticeDTO;
import com.kh.myapp.notice.NoticeSvc;
import com.kh.myapp.util.RecordCriteria;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/notice")
public class NoticeController {
	
	private static Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Inject
	NoticeSvc nsvc;
	
	//게시글 등록양식-write_form
	@RequestMapping("/write")
	public String write(Model model) {
		log.info("notice/write 호출");
		NoticeDTO noticedto = new NoticeDTO();
		model.addAttribute("noticeDTO",noticedto);		
		return "/notice/writeForm";		
	}
	
	
	
	//게시글 등록처리
	@RequestMapping("/writeOK")
	public String writeOK(@Valid @ModelAttribute("noticeDTO") NoticeDTO noticeDTO, @AuthenticationPrincipal MemberDTO mdto, BindingResult result, Model model, HttpSession session) {
		logger.info("String writeOK 호출됨:" + noticeDTO);
		
		int cnt = 0;
		if(result.hasErrors()) {
			logger.info(result.toString());
			return "/notice/writeForm";
		}
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails)principal;
		log.info("case1" + mdto);
//		log.info("case2" + MemberDTO.current()); 왜 오류가 났냐면 MemberDTO에 맨마지막 user로 담아서 그렇습니다. member로 바꾸면 안남.
		log.info("case3" + userDetails);
		
		// 사용자 세션정보가 없는경우
		if(mdto == null) {
			return "redirect:/login/loginForm";
		}
		try {
						
			cnt = nsvc.write(noticeDTO);
			logger.info("게시글 등록건수 :" + cnt);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return "redirect:/notice/list";
	}	
	
	
	
	//게시글 보기-read.jsp
	@RequestMapping("/view")
	public String view(@ModelAttribute NoticeDTO noticeDTO, Model model, HttpServletRequest request){
		
		
		RecordCriteria rc = null;
		try {
			rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
			noticeDTO = nsvc.view(request.getParameter("nnum"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		model.addAttribute("noticeDTO",noticeDTO);
		model.addAttribute("rc",rc);
		
		return "/notice/read";
	}
	
	
	//게시글 수정양식
	@RequestMapping("/modifyOK")
	public String modifyOK(@Valid @ModelAttribute NoticeDTO noticeDTO, BindingResult result) {
		
		if(result.hasErrors()) {

			return "/notice/read";
			
		}
		try {
			nsvc.modify(noticeDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "forward:/notice/view";
	}
	
	
	//게시글 삭제처리
	@RequestMapping("/delete")
	public String delete(@RequestParam("nnum") String nnum,
			@RequestParam("reqPage") String reqPage) {
		
		
		try {
			nsvc.delete(nnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/notice/list?reqPage="+reqPage;
	}
	
	
	
	
	
	
	
	//게시글 목록보기-list.jsp
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		
		try {
			nsvc.list(request, model);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return "/notice/list";
		
	}
	

	

}
