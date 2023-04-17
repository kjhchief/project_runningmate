package com.runningmate.web.mate.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runningmate.domain.mate.dto.Mate;
import com.runningmate.domain.mate.service.MateService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
//@SessionAttributes("mate")
@RequestMapping("/mate")
public class MateController {

	@Autowired
	private MateService mateService;

	// 회원가입 화면 요청에 대한 처리 메소드
	@GetMapping
	public String registerForm() {
		return "mate/register";

	}
	
	// 로그인 화면 요청에 대한 처리 메소드
	@GetMapping("/login") //@CookieValue브러우저에 저장되어있는 쿠키정보 불러옴
	public String loginForm(@CookieValue(value="savedEmail", required = false) String savedEmail, Model model) {
		log.info("savedEmail : {}", savedEmail);
		
		if(savedEmail != null) {
			model.addAttribute("saveEmail", savedEmail);
		}
		
		return "mate/login";

	}

	// 이메일 유효성 검사
	@PostMapping("/email-check")
	@ResponseBody
	public String emailCheck(@RequestParam String email) {
		if (mateService.existEmail(email)) {
			return "false";
		} else {
			return "true";
		}
	}

	// 회원가입 처리 메소드
	@PostMapping
	public String register(@ModelAttribute("mate") Mate mate) {
		mate.setBirthdate();
		mate.setLocation();
		mate.setPhoneNumber();
		log.info("mate {}", mate);
		mateService.create(mate);
		return "/mate/login";
	}
	
	//로그인 처리 메소드
	@PostMapping("/login")
	@ResponseBody
	public String Login(@RequestParam String email, @RequestParam String password, 
						@RequestParam(required = false) String saveEmail,
						HttpSession session, HttpServletResponse response) {
		
		
		// 회원인 경우
		Mate mate = mateService.getLoginInfo(email, password); //변수처리안하고 if문에 넣으면 DB 두 번 갔다옴
		if(mate != null) {
			session.setAttribute("mate", mate);

			if (saveEmail != "") {
				log.info("쿠키저장");

				Cookie cookie = new Cookie("savedEmail", mate.getEmail());
				cookie.setMaxAge(60 * 60 * 1 * 1); // 1시간
				cookie.setPath("/");
				response.addCookie(cookie);				
			}else {
				log.info("쿠키저장안됨");
			}	
			return "true";
			
		}else {// 회원이 아닌 경우
			return "false";
		}
			
	}
	
	   // 카카오 로그인 처리 메소드
    @PostMapping("/kakaoLogin")
    @ResponseBody
    public String kakaoLogin(@ModelAttribute Mate kakaoMate, HttpSession session) {
        if(kakaoMate != null) {
    	session.setAttribute("kakaoMate", kakaoMate);
    	kakaoMate.setKakaoaccYn("Y");
    	log.info("kakaoMate : {}", kakaoMate);
        return "true";
        }else {
        	return "false";
        }
    }

	
	
	// 회원가입결과에 대한 메소드
	@GetMapping("/main")
	public String registerResult(HttpSession session, Model model) {
	Mate mate = (Mate)session.getAttribute("mate");
	String kakaoAccount = (String)session.getAttribute("kakaoAccount");
	model.addAttribute("mate", mate);
	model.addAttribute("kakaoAccount", kakaoAccount);
		log.info("mate : {}", mate);
		log.info("kakaoAccount : {}", kakaoAccount);
		
		return "/";
	}
	
	//마이페이지
	@GetMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		//일반로그인 세션
		Mate mate = (Mate)session.getAttribute("mate");
		if(mate != null) {
		model.addAttribute("mate", mate);
			log.info("mate : {}", mate);
		}else {
		//카카오 세션	
		Mate kakaoMate = (Mate)session.getAttribute("kakaoMate");
		model.addAttribute("kakaoMate", kakaoMate);
		log.info("kakaoMate : {}", kakaoMate);
		}
		return "/mate/mypage" ;
	}
	
	//mate정보 수정 페이지 화면 처리
	@GetMapping("/mateDetail")
	public String mateDatailForm(HttpSession session, Model model) {
		Mate mate = (Mate)session.getAttribute("mate");
		if(mate != null) {
		mate.setAddress();
		mate.setAddressDetail();
		model.addAttribute("mate", mate);
		log.info("mate : {}", mate);	
		}else {
		//카카오 세션
		Mate kakaoMate = (Mate)session.getAttribute("kakaoMate");
		model.addAttribute("kakaoMate", kakaoMate);
		log.info("kakaoMate : {}", kakaoMate);	
		}
		return "/mate/mateDetail";
	}
	
	//mate정보 수정 기능  처리
	@PostMapping("/mateDetail")
	public String mateDatail(HttpSession session, Model model, 
							@RequestParam(name="new-password", required=false) String newPassword, 
							@ModelAttribute("mate") Mate mate) {
		log.info("제발제발 : {}", mate);
		mate.setLocation();
		mate.setPassword(newPassword);
		
		Mate updatedMate = mateService.update(mate); // 1. update된 Mate 객체를 반환
		updatedMate.setAddress();
		updatedMate.setAddressDetail();
		session.setAttribute("mate", updatedMate); // session에도 update된 Mate 객체를 저장
		model.addAttribute("mate", updatedMate); // 3. model.addAttribute에 새로운 Mate 객체를 저장
		log.info("업데이트야 제발 되그라 :{} ", mate);
	
		if(newPassword != null) {
			
			return "redirect:/mate/newLogin"; // 비밀번호 변경 후 로그인 페이지로 이동
		}

	        return "/mate/mateDetail"; // 기존의 mateDetail 페이지로 이동
	    
	}
	
	//비밀번호 확인
	@PostMapping("/password-check")
	@ResponseBody
	public String passwordCheck(@RequestParam String email, @RequestParam String password) {
		if (mateService.existPassword(email, password)) {
			return "true";
		} else {
			return "false";
		}
	}
	
	//회원탈퇴
		@PostMapping("/delete")
		@ResponseBody
		public String mateDelete(@RequestParam String email) {
			if (mateService.isDelete(email)) {
				return "true";
			} else {
				return "false";
			}
		}
		//mate정보 수정 기능 처리
		@GetMapping("/newLogin")
		public String deleteSuccess(HttpSession session) {
			 session.invalidate(); // 세션 삭제
			 return "/mate/login"; // 비밀번호 변경 후 로그인 페이지로 이동
		}
		
		//이메일,비밀번호 찾기 화면 처리
		@GetMapping("/findEmailPassword")
		public String findEmailPasswordForm() {
			
		return "/mate/findEmailPassword"; 
		}
		
		
		//이메일, 비밀번호 찾기 기능 처리
		@PostMapping("/findEmailPassword")
		@ResponseBody
		public String findEmailPassword(@RequestParam String name,
				                        @RequestParam(required=false) String email, 
										@RequestParam(required=false) String password) {
		log.info("이름: {}", name);
		log.info("이멜: {}", email);
		log.info("비번: {}", password);
		// 이메일 찾기
		if(email == null ) {
			String emailResult = mateService.findEmail(name, password);
			if (emailResult != null) {
				log.info("email : {}", emailResult);	
				return "{\"type\" : \"email\", \"result\" : true, \"email\" : \""+emailResult+"\" }"; //script에서 true는 "" 처리 안되있어서 true로 인식함
			}else {
				log.info("email : {}", emailResult);	
				return "{\"type\" : \"email\", \"result\" : false}";
			}
		}else {// 비빌번호 찾기
			String passwordResult = mateService.findPassword(name, email);
			if(passwordResult != null) {
				log.info("password : {}", passwordResult);	
				return "{\"type\" : \"password\", \"result\" : true, \"password\" : \""+passwordResult+"\" }";
			}else {
				log.info("password : {}", passwordResult);	
				return "{\"type\" : \"password\", \"result\" : false}";
			}
		}
		
	}
		
		//모임찜한목록페이지
		@GetMapping("/wishlist")
		public String wishlistForm() {
		return "/crew/wishlist"; 
		}
	
		//모임신청목록페이지
		@GetMapping("/crewList")
		public String crewListForm() {
		return "/crew/crewList"; 
		}
				
}
