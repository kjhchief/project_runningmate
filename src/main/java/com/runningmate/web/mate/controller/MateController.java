package com.runningmate.web.mate.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@SessionAttributes("mate")
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

	// 회원가입 처리에 대한 메소드
	@PostMapping
	public String register(@ModelAttribute("mate") Mate mate) {
		mate.setBirthdate();
		mate.setLocation();
		mate.setPhoneNumber();
		log.info("mate {}", mate);
		mateService.create(mate);
		return "redirect:/mate/result";
	}
/*
	@PostMapping
	@ResponseBody
	public String Login(@ModelAttribute("mate") Mate mate) {
		mate = mateService.getLoginInfo(mate.getEmail(), mate.getPassword());
		return "mate";
		
	}
	*/
	// 회원가입결과에 대한 메소드
	@GetMapping("/result")
	public String registerResult() {
		return "main";
	}

}
