package com.kh.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.myapp.member.dto.MemberDTO;
import com.kh.myapp.member.dto.MemberIDPW;
import com.kh.myapp.member.service.MemberSvc;
import com.kh.myapp.util.Code;

@Controller
@RequestMapping("/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	private MemberSvc memberSvc;
	


	// 회원등록양식
	@RequestMapping("/memberJoinForm")
	public String memberJoinForm(Model model) {
		logger.info("memberJoinForm() 호출됨!");
		model.addAttribute("mdto", new MemberDTO());

		return "/member/memberJoinForm";
	}

	@ModelAttribute
	public void initData(Model model) {
		// 지역
		List<Code> rCodes = new ArrayList<>();
		rCodes.add(new Code("서울", "서울"));
		rCodes.add(new Code("경기", "경기"));
		rCodes.add(new Code("인천", "인천"));
		rCodes.add(new Code("대전", "대전"));
		rCodes.add(new Code("충북", "충북"));
		rCodes.add(new Code("충남", "충북"));
		rCodes.add(new Code("전남", "전남"));
		rCodes.add(new Code("전북", "전북"));
		rCodes.add(new Code("경남", "경남"));
		rCodes.add(new Code("경북", "경북"));
		rCodes.add(new Code("경북", "경북"));
		rCodes.add(new Code("강원", "강원"));
		rCodes.add(new Code("대구", "대구"));
		rCodes.add(new Code("울산", "울산"));
		rCodes.add(new Code("부산", "부산"));
		rCodes.add(new Code("제주", "제주"));

		// 성별
		List<Code> gender = new ArrayList<>();
		gender.add(new Code("남", "남자"));
		gender.add(new Code("여", "여자"));

		model.addAttribute("rCodes", rCodes);
		model.addAttribute("gender", gender);
	}

	// 회원등록처리
	@RequestMapping(value = "/memberJoin", method = RequestMethod.POST)
	public String memberJoin(@Valid @ModelAttribute("mdto") MemberDTO mdto, BindingResult result, Model model) {
		logger.info("/member/memberJoin 호출됨!");
		logger.info(mdto.toString());
		boolean success = false;

		if (result.hasErrors()) {

			logger.info(result.toString());
			logger.info("회원가입시 오류발생!!");
			return "/member/memberJoinForm";
		}
		logger.info("mdto: "+mdto);
		success = memberSvc.insert(mdto);
		model.addAttribute("result", success);
		return "redirect:/";
	}

	// 회원목록조회
	@RequestMapping("/memberList")
	public String memberList(Model model) {

		List<MemberDTO> list = memberSvc.getMemberList();
		model.addAttribute("memberList", list);

		return "/member/memberList";
	}

	// 회원정보잠금페이지
	@RequestMapping("/memberModifyFormView/{id:.+}")
	public String memberModifyFormview(@PathVariable String id, Model model) {
		logger.info("/memberModifyViewForm");

		MemberDTO mdto = memberSvc.getMember(id);
		model.addAttribute("mdto", mdto);
		return "/member/memberModifyFormlock";
	}

	// 회원정보수정페이지
	@RequestMapping(value = "/memberModifyForm/{id:.+}")
	public String memberModifyForm(@PathVariable String id, Model model) {
		logger.info("/memberModifyForm");

		MemberDTO mdto = memberSvc.getMember(id);
		model.addAttribute("mdto", mdto);
		return "/member/memberModifyForm";
	}
	
	
	// 회원수정처리
	@RequestMapping(value = "/memberModify", method = RequestMethod.POST)
	public String memberModify(@Valid @ModelAttribute("mdto") MemberDTO mdto, BindingResult result, HttpSession session) {
		logger.info("/memberModify");
		boolean success = false;

		if (result.hasErrors()) {
			return "/member/memberModify";
		}
		success = memberSvc.modify(mdto);
		
		if(success) {
			session.removeAttribute("user");
			mdto = memberSvc.getMember(mdto.getId());
			session.setAttribute("user", mdto);
		}

		logger.info("수정처리 결과:" + success);
		return "/member/memberModifyFormView/{id:.+}";//이전에 요청한 정보까지 담아서 전달 forward:/member/memberModifyFormView/{id:.+}
//		return "redirect:/member/memberList";//후에 url경로가 바뀜 memberlist로 바뀜
//		return "/member/memberList";//그냥 아무것도없는 리스트만보여줌
	}
	
	
	
	//비밀번호 변경시 비밀번호폼 화면
	@RequestMapping("/pwCheckForm")
	public void checkForm(Model model) {
		logger.info("/member/check() 호출됨!");
		model.addAttribute( "mdto" ,new MemberDTO());
	}
	
	
	//비밀번호 변경하기
	@RequestMapping(value = "/checkOK", method = RequestMethod.POST)
	public String checkOK(@ModelAttribute("mdto") MemberDTO mdto, BindingResult result, HttpSession session) {
		logger.info("/member/checkOK 호출됨!");
		boolean success = false;
		
		if(result.hasErrors()) {
			logger.info(result.toString());
			return "/member/pwCheckForm";
		}
		
		success = memberSvc.pwmodify(mdto);		
		
		if(success) {
			session.removeAttribute("user");
			mdto = memberSvc.getMember(mdto.getId());
			session.setAttribute("user", mdto);
		}

		
		return "/member/pwCheckForm2";
	}	
	
	

	// 회원삭제처리
	@RequestMapping("/memberDelete/{id:.+}")
	public String memberDelete(@PathVariable String id, HttpSession session) {
		logger.info("/memberDelete/{id:.+}");
		boolean success = false;

		success = memberSvc.adminDelete(id);
		
		if(success) {
			session.removeAttribute("user");
		}
		logger.info("삭제처리 결과:" + success);
		return "redirect:/";
	}
	
	// 아이디찾기 양식
	@RequestMapping("/findidForm")
	public String FindidForm(Model model) {
		logger.info("findidForm() 호출됨!");
		model.addAttribute("mdto", new MemberDTO());

		return "/member/findIDForm";
	}
	
	// 아이디찾기
	@RequestMapping(value="/findid", method=RequestMethod.POST)
	public String findid(@ModelAttribute("mdto") MemberIDPW midpw, String tel, String birth, Model model) {
		logger.info("/member/Findid 호출");
		MemberDTO mdto = memberSvc.findID(midpw.getTel(), midpw.getBirth());
		model.addAttribute("find", mdto);		
		
		if(mdto==null) {
			return "/login/findidfail";
		}
		
		return "/login/findid";
		
	}
	
	// 비밀번호찾기 양식
	@RequestMapping("/findpwForm")
	public String FindpwForm(Model model) {
		logger.info("findpwForm() 호출됨!");
		model.addAttribute("mdto", new MemberDTO());

		return "/member/findPWForm";
	}
	
	
	// 비밀번호 찾기
	@RequestMapping(value="/findpw", method=RequestMethod.POST)
	public String findpw(@ModelAttribute("mdto") MemberIDPW midpw, String id, String tel, String birth, Model model) {
		logger.info("/member/findpw 호출됨!");
		MemberDTO mdto = memberSvc.findPW(midpw.getId(), midpw.getTel(), midpw.getBirth());
		model.addAttribute("find", mdto);	
		
		if(mdto==null) {
			return "/login/findpwfail";
		}
		
		return "/login/findpw";
	}
	
	
	//Model model은 뷰페이지 전달위한거라서 여기선 필요없다
	//이미지 업로드
	//@RequestMapping(value="/upload",method=RequestMethod.POST) 
	//responsebody 뷰를전달하지않고 데이타만 전달
	@PostMapping("/upload")
	@ResponseBody  //RestFul 서비스(뷰를 리턴하지않고 httpStatus값을 리턴하도록 설계)
	public ResponseEntity<String> doUpload(@RequestParam("file") MultipartFile file) {
		
		ResponseEntity<String> resCode = null;
		String randomFileName = null;		//난수 파일명
		String originFileName = null;		//초기 파일명
		String faleLocation = "D:\\KHR\\git\\repository\\springedu2\\src\\main\\webapp\\resources\\upload";
		
		if(!file.isEmpty()) {
			
			randomFileName = UUID.randomUUID().toString();
			originFileName = file.getOriginalFilename();
			
			//초기파일명에서 확장자 추출
			int pos = originFileName.lastIndexOf(".");
			String ext = originFileName.substring(pos + 1);
			randomFileName = randomFileName + "." + ext;
			
			
			File tmpFile = new File(faleLocation,randomFileName);
			try {
				file.transferTo(tmpFile);
				resCode = new ResponseEntity<String>("success",HttpStatus.OK);
			} catch (IOException e) {
				e.printStackTrace();
				resCode = new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
				return resCode;
			} 
		}
		
		return resCode;
	}
	
	
}
