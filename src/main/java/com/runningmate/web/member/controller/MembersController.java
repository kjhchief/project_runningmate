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
@RequestMapping("/members")
public class MembersController {

	@Autowired
	private MemberService memberService;

	// 회원가입 화면 요청에 대한 처리 메소드
	@GetMapping
	public String registerForm() {
		return "member/joinmembership";

	}

	// 이메일 유효성 검사
	@PostMapping("/email-check")
	@ResponseBody
	public String emailCheck(@RequestParam String email) {

		boolean exist = memberService.existEmail(email);
		log.info("존재여부 {}, ", exist);

		if (memberService.existEmail(email)) {
			return "false";
		} else {
			return "true";
		}
	}

	// 회원가입 처리에 대한 메소드
	@PostMapping
	public String register(@ModelAttribute("members") Members members) {
		members.setBirthdate();
		members.setLocation();
//		log.info("members {}", members);
		memberService.create(members);
		return "redirect:/members/result";
	}

	// 회원가입결과에 대한 메소드
	@GetMapping("/result")
	public String registerResult() {
		return "member/member-result";
	}

}
