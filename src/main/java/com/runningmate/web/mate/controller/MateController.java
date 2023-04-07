package com.runningmate.web.mate.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

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
	@GetMapping("/login")
	public String loginForm() {
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
		return "redirect:/mate/main";
	}
	//로그인 처리 메소드
	@PostMapping("/login-check")
	@ResponseBody
	public String Login(@ModelAttribute("mate") Mate mate, 
			HttpSession session, HttpServletResponse response, 
			@CookieValue(value = "saveEmail", required = false) String saveEmail, 
			Model model) {
		
		// required = false 파라미터가 필수적이지 않음을 명시
		if(mateService.getLoginInfo(mate) != null) {
			session.setAttribute("mate", mateService.getLoginInfo(mate));
			
			if (saveEmail != null) {
				
				log.info("쿠키{}", saveEmail);
				Cookie cookie = new Cookie("savedEmail", mate.getEmail());
				cookie.setMaxAge(60 * 60 * 1 * 1); // 1시간
				cookie.setPath("/");
				response.addCookie(cookie);
				
				 model.addAttribute("savedEmail", saveEmail);
			}
			
			return "success";
		}else {
			return "failure";
		}
		
	}
	
	// 회원가입결과에 대한 메소드
	@GetMapping("/main")
	public String registerResult() {
		return "main";
	}

}
