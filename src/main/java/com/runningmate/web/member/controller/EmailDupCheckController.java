package com.runningmate.web.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runningmate.domain.member.dto.Members;
import com.runningmate.domain.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/members2")
public class EmailDupCheckController {
	
	@Autowired
	private MemberService memberService;
	
	// 회원가입 화면 요청에 대한 처리 메소드
	@GetMapping
	public String registerForm() {
		return "member/joinmembership";
		
	}
	
	//이메일 유효성 검사
	@PostMapping
	@ResponseBody
	public String emailCheck(@RequestParam String email) {
		String usableMessage=null;
		if(memberService.existEmail(email)) {
			return usableMessage = "사용중인 이메일입니다.";
		}else {
			return usableMessage = "사용가능한 이메일입니다.";
		}
	}

	
	
}
